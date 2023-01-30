import { Component } from '@angular/core';
import { heroCount } from './Hero';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'day31workshop';

  counts: heroCount[] = [ { name:"superman", count:0 },
                          { name:'flash', count: 0},
                          { name: 'wonderwoman', count: 0},
                          { name: 'spiderman', count: 0 } ]
  totalCount: number = 0

  addHero(name: string){ 
    const heroIndex = this.counts.findIndex( Hero => Hero.name == name)
    console.info("check index ", heroIndex)
    this.counts[heroIndex].count++
    this.totalCount++
    console.info(this.counts[heroIndex]) // = { name: name, count: 1 }
  }

  reduceHero(name: string){
    const heroIndex = this.counts.findIndex( Hero => Hero.name == name)
    console.info("check index ", heroIndex)
    if(this.counts[heroIndex].count > 0)
    { this.counts[heroIndex].count-- 
      this.totalCount--             }
    console.info(this.counts[heroIndex])
  }
}
