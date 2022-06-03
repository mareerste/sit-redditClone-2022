export class Flair {
    id:number;
    name:string;
    public toString = () : string => {
        return `(${this.name})`;
    }
}
