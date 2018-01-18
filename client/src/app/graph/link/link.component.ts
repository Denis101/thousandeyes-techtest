import { Component, Input } from '@angular/core';
import { Link } from '../../model/d3/Link';

@Component({
    selector: '[linkVisual]',
    templateUrl: './link.component.ts',
    styleUrls: ['./link.component.css']
})
export class LinkComponent {
    @Input('linkVisual') link: Link;
}