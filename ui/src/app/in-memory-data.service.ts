import { InMemoryDbService } from 'angular2-in-memory-web-api'

export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    let articles = [
      {
        id: 1,
        title: "Test",
        content: "Blablabla..."
      }
    ]
    
    return {
      articles
    }
  }
}