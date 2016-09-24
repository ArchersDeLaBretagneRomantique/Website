import { Component, OnInit } from '@angular/core'
import { ActivatedRoute, Params } from '@angular/router';

import { Album, AlbumService } from '../shared'

@Component({
    selector: 'abr-photos',
    providers: [AlbumService],
    templateUrl: './photos.component.html'
})
export class PhotosComponent implements OnInit {
    album: Album

    constructor(
      private albumService: AlbumService,
      private route: ActivatedRoute
    ) {}

    getAlbum() {
        this.route.params.forEach((params: Params) => {
            let id = +params['id']
            this.albumService.getAlbum(id)
                .then(album => this.album = album)
        })
    }

    ngOnInit(): void {
      this.getAlbum()
    }
}