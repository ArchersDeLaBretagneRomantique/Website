import { Component, Input } from '@angular/core'

import { Photo } from '../shared'

@Component({
    selector: 'abr-photo',
    templateUrl: './photo.component.html'
})
export class PhotoComponent {
    @Input()
    photo: Photo

    getFile() {
        return `/photos/${this.photo.file}`
    }
}