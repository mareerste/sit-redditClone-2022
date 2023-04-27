import { Observable } from 'rxjs';
import { Comment } from 'src/app/model/comment';
import { Flair } from './flair';
import { Reaction } from './reaction';
import { User } from './user';

export class Post {
    id:number;
    title: string;
    text:string;
    creationDate:Date;
    imagePath:string;
    deleted:boolean;
    user: User;
    flair: Flair;
    comments?:Comment[] | [];
    reactions:number;
    files:File
}
