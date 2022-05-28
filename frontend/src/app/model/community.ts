import { User } from './user';

import { Flair } from './flair';
export class Community {
    id:number;
    name:string;
    description:string;
    creationDate:Date;
    rules?:string[];
    isSuspended:boolean;
    suspendedReason?:string;
    flairs:Flair[]|[];
    moderators:User[]|[];
}
