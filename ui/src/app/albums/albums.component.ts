import { Component, OnInit } from '@angular/core'

import { Album, AlbumService } from './shared'

@Component({
    selector: 'albums',
    providers: [AlbumService],
    templateUrl: './albums.component.html'
})
export class AlbumsComponent implements OnInit {
    albums: Album[] = []

    constructor(
      private albumService: AlbumService
    ) {}

    getAlbums(): void {
      this.albumService.getAlbums()
        .then(albums => this.albums = albums)
    }
  
    ngOnInit(): void {
      this.getAlbums()
    }
}