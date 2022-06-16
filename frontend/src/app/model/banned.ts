import { Community } from "./community";
import { User } from "./user";

export class Banned {
    id:number;
    timestamp?:string;
    moderator?: User;
    community:Community;
    user:User;
}
