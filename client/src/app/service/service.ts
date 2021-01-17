export interface ServiceInterface {
    id?: number;
    url?: string;
    name?: string;
    creationDate?: Date;
    lastUpdated?: Date;
    status?: string;
}

export class Service implements ServiceInterface {
    id?: number;
    url?: string;
    name?: string;
    creationDate?: Date;
    lastUpdated?: Date;
    status?: string;

    public constructor(init?: Partial<Service>) {
        Object.assign(this, init);
    }
}