import { Post } from './post';
import { User } from './user';
import { ReactionType } from './enums/reaction-type';
export class Reaction {
    id:number;
    type:ReactionType;
    timestamp:Date;
    user:User;
    post?:Post;
    comment?:Comment;
}
