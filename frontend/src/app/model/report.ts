import { Comment } from 'src/app/model/comment';

import { ReportReason } from "./enumerations/report-reason.enum";
import { Post } from "./post";
import { User } from "./user";

export class Report {
    id:number;
    reason:ReportReason;
    timestamp:Date;
    user:User;
    accepted?:Boolean;
    post?:Post;
    comment?:Comment;
}
