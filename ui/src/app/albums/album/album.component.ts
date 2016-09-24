import { Component, Input } from '@angular/core'
import { Router } from '@angular/router';

import { Album } from '../'

@Component({
    selector: 'album',
    templateUrl: './album.component.html'
})
export class AlbumComponent {
    @Input()
    album: Album

    constructor(
        private router: Router
    ) {}

    onClick() {
        this.router.navigate(['/albums', this.album.id])
    }
}