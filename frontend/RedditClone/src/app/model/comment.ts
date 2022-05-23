import { User } from './user';
export class Comment {
    id:number;
    text:string;
    timestamp:Date;
    isDeleted:boolean;
    user:User;
    childComments?: Comment[];
}
