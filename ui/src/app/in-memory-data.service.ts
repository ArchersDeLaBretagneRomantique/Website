import { InMemoryDbService } from 'angular2-in-memory-web-api'

export class InMemoryDataService implements InMemoryDbService {
    createDb() {
        let albums = [
            {
                id: 1,
                title: 'Albums',
                photos: [{
                    id: 1,
                    file: 'test'
                }]
            }
        ]

        let articles = [
            {
                id: 1,
                title: 'Article',
                content: 'Blablabla...'
            }
        ]
    
        return {
            albums,
            articles
        }
    }
}