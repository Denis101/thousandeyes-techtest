import { Component, Input } from '@angular/core';
import { Node } from '../../model/d3/Node';

@Component({
    selector: '[nodeVisual]',
    templateUrl: './node.component.ts',
    styleUrls: ['./node.component.css']
})
export class NodeComponent {
    @Input('nodeVisual') node: Node;
}