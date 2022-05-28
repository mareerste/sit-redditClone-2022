import { ReactionType } from './enumerations/reaction-type.enum';
import { Post } from './post';
import { User } from './user';
export class Reaction {
    id:number;
    type:ReactionType;
    timestamp:Date;
    user:User;
    post?:Post;
    comment?:Comment;
}
