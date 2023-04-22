package rs.ac.uns.ftn.informatika.redditClone.service.elasticsearch;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.informatika.redditClone.lucene.handlers.DocumentHandler;
import rs.ac.uns.ftn.informatika.redditClone.lucene.handlers.PDFHandler;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.CommunitySearchDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.CommunityWithFlairsDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.PostCreateDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.dto.PostSearchDTO;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.CommunityES;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.Post;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.PostES;
import rs.ac.uns.ftn.informatika.redditClone.repository.PostESRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceES {

    @Value("${files.path}")
    private String filesPath;
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    private final PostESRepository postESRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public PostServiceES(PostESRepository postESRepository, ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.postESRepository = postESRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }
    public void index (Post post){postESRepository.save(new PostES(post));}
    public void index (PostES post){postESRepository.save(post);}

    public PostES findPostById(Integer id){
        Optional<PostES> postESOptional = postESRepository.findById(id);
        if(postESOptional.isPresent()){
            return postESOptional.get();
        }else{
            return null;
        }
    }

    private List<PostSearchDTO> mapPostESToPostSearchDTO(List<PostES> posts){
        List<PostSearchDTO> postSearchDTOS = new ArrayList<>();
        for(PostES post : posts){
            postSearchDTOS.add(new PostSearchDTO(post));
        }
        return postSearchDTOS;
    }

    public List<PostSearchDTO> findPostsByTitle(String title){
        return mapPostESToPostSearchDTO(postESRepository.findAllByTitleContaining(title));
    }

    public List<PostSearchDTO> findPostsByFlair(String flair){
        return mapPostESToPostSearchDTO(postESRepository.findAllByFlair(flair));
    }

    public List<PostSearchDTO> findPostsByText(String text){
        return mapPostESToPostSearchDTO(postESRepository.findAllByTextContainingOrTextFileContaining(text));
    }

    public List<PostSearchDTO> findPostsByKarmaRange(Integer min, Integer max){
        return mapPostESToPostSearchDTO(postESRepository.findByKarmaInRange(min, max));
    }

    public List<PostSearchDTO> findPostsByCommentsRange(Integer min, Integer max){
        return mapPostESToPostSearchDTO(postESRepository.findByCommCountInRange(min, max));
    }

    public void deletePostById(Integer id){
        postESRepository.deleteById(id);
    }

    public List<PostSearchDTO> searchFuzzyPosts(String title, String text, Integer minKarma, Integer maxKarma) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.fuzzyQuery("title", title))
                .must(QueryBuilders.fuzzyQuery ("text", text));
        if (minKarma != null && maxKarma != null) {
            List<PostES> postsWithKarmaRange = postESRepository.findByKarmaInRange(minKarma, maxKarma);
            queryBuilder.filter(QueryBuilders.termsQuery("id", postsWithKarmaRange.stream().map(PostES::getId).toArray(Integer[]::new)));
        }

        Query query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        if (elasticsearchOperations == null) {
            throw new NullPointerException("elasticsearchOperations is null");
        }

        SearchHits<PostES> searchHits = elasticsearchOperations.search(
                query,
                PostES.class
        );

        List<PostES> retVal = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
        return mapPostESToPostSearchDTO(retVal);
    }

    public List<PostSearchDTO> searchPhrasePosts(String title, String text, Integer minKarma, Integer maxKarma) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchPhraseQuery("title", title))
                .must(QueryBuilders.matchPhraseQuery ("text", text));
        if (minKarma != null && maxKarma != null) {
            List<PostES> postsWithKarmaRange = postESRepository.findByKarmaInRange(minKarma, maxKarma);
            queryBuilder.filter(QueryBuilders.termsQuery("id", postsWithKarmaRange.stream().map(PostES::getId).toArray(Integer[]::new)));
        }

        Query query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        if (elasticsearchOperations == null) {
            throw new NullPointerException("elasticsearchOperations is null");
        }

        SearchHits<PostES> searchHits = elasticsearchOperations.search(
                query,
                PostES.class
        );

        List<PostES> retVal = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());
        return mapPostESToPostSearchDTO(retVal);
    }

    public void indexUploadedFile(PostCreateDTO postDTO) throws IOException {
        for (MultipartFile file : postDTO.getFiles()) {
            if (file.isEmpty()) {
                continue;
            }

            String fileName = saveUploadedFileInFolder(file);
            if(fileName != null){
                PostES post = getHandler(fileName).getIndexUnitForPost(new File(fileName));
                post.setId(postDTO.getId());
                post.setText(postDTO.getText());
                post.setTitle(postDTO.getTitle());
                post.setKarma(1);
                post.setFlair(postDTO.getFlair().getName());
                index(post);
            }
        }
    }

    private String saveUploadedFileInFolder(MultipartFile file) throws IOException {
        String retVal = null;
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(new File(filesPath).getAbsolutePath() + File.separator + file.getOriginalFilename());
            Files.write(path, bytes);
            retVal = path.toString();
        }
        return retVal;
    }

    public DocumentHandler getHandler(String fileName){
        return getDocumentHandler(fileName);
    }

    public static DocumentHandler getDocumentHandler(String fileName) {
        if(fileName.endsWith(".pdf")){
            return new PDFHandler();
        }else{
            return null;
        }
    }

}
