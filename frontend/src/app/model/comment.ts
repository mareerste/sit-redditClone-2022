import { User } from './user';
export class Comment {
    id?:number;
    text?:string;
    timestamp?:Date;
    deleted?:boolean;
    user?:User;
    childComments?: Comment[];
    reactions:number;
}
