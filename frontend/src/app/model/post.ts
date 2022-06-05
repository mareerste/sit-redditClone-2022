import { Observable } from 'rxjs';
import { Flair } from './flair';
import { Reaction } from './reaction';
import { User } from './user';

export class Post {
    id:number;
    title: string;
    text:string;
    creationDate:string;
    imagePath:string;
    isDeleted:boolean;
    user: User;
    flair: Flair;
    comments?:Comment[] | [];
    reactions:number;
}
