import { Component } from '@angular/core'

@Component({
    selector: 'abr-footer',
    templateUrl: './footer.component.html',
    styleUrls: ['./footer.component.css']
})
export class FooterComponent { 
    getCurrentYear() {
        const d = new Date()
        return d.getFullYear()
    }
}
