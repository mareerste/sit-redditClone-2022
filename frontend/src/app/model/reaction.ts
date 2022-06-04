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

    private static keys = ["id","type", "timestamp", "user","post","comment"];

    constructor(type: ReactionType,post?:Post, comment?:Comment) {
        this.type = type;
        this.post = post;
        this.comment = comment;
      }

    toJsonString(): string {
        const data = {};
        Reaction.keys.forEach(key => data[key] = this[key]);
        console.log(data)
        return JSON.stringify(data);
    }
}
