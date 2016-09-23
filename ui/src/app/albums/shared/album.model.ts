import { Photo } from '../photos'

export class Album {
    constructor(
        public id: number,
        public title: string,
        public photos: Photo[]
    ) {}
}