import { Component, Input } from '@angular/core'

import { Album } from '../'

@Component({
    selector: 'album',
    templateUrl: './album.component.html'
})
export class AlbumComponent {
    @Input()
    album: Album
}