import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { WebcamImage } from 'ngx-webcam';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent  {

  @Input() image!:WebcamImage | null


}
