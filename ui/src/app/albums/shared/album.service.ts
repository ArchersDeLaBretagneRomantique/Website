import { Injectable } from '@angular/core'
import { Http }    from '@angular/http'

import 'rxjs/add/operator/toPromise';

import { Album } from './album.model'

@Injectable()
export class AlbumService {
    constructor(
        private http: Http
    ) {}

    private handleError(error: any): Promise<any> {
        return Promise.reject(error.message || error);
    }

    getAlbums(): Promise<Album[]> {
        return this.http.get('/api/albums')
            .toPromise()
            .then(response => response.json().data as Album[])
            .catch(this.handleError)
    }

    getAlbum(id: number): Promise<Album> {
        return this.getAlbums()
            .then(albums => albums.find(album => album.id === id))
    }
}
