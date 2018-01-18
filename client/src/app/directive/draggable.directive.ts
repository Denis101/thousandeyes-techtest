import { Directive, Input, ElementRef } from '@angular/core';
import { Node } from '../model/d3/Node';
import { ForceDirectedGraph } from '../model/d3/ForceDirectedGraph';
import { D3Service } from '../service/d3.service';

@Directive({
    selector: '[draggableNode]'
})
export class DraggableDirective {
    @Input('draggableNode') draggableNode: Node;
    @Input('draggableInGraph') draggableInGraph: ForceDirectedGraph;

    constructor(private d3Service: D3Service, private _element: ElementRef) {}

    ngOnInit() {
        this.d3Service.applyDraggableBehaviour(
            this._element.nativeElement, 
            this.draggableNode, 
            this.draggableInGraph);
    }
}