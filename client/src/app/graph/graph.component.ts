import { Component, Input } from "@angular/core";
import { ForceDirectedGraph } from "../model/d3/ForceDirectedGraph";
import { D3Service } from "../service/d3.service";


@Component({
    selector: 'graph',
    templateUrl: './graph.component.html',
    styleUrls: ['./graph.component.css']
})
export class GraphComponent {
    @Input('nodes') nodes;
    @Input('links') links;

    graph: ForceDirectedGraph;

    constructor(private d3Service: D3Service) {}

    ngOnInit() {
        this.graph = this.d3Service.getGraph(
            this.nodes, 
            this.links, 
            this.options);
    }

    ngAfterViewInit() {
        this.graph.initSimulation(this.options);
      }

    private _options: { width, height } = { width: 800, height: 600 };

    get options() {
        return this._options = {
            width: window.innerWidth,
            height: window.innerHeight
        }
    }
}