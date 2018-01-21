import { Component, Input } from "@angular/core";
import { ForceDirectedGraph } from "../model/d3/ForceDirectedGraph";
import { D3Service } from "../service/d3.service";
import { Node } from "../model/d3/Node";
import { Link } from "../model/d3/Link";
import { PersonService } from "../service/person.service";


@Component({
    selector: 'graph',
    templateUrl: './graph.component.html',
    styleUrls: ['./graph.component.css']
})
export class GraphComponent {
    nodes: Node[] = [];
    links: Link[] = [];
    graph: ForceDirectedGraph;

    constructor(private d3Service: D3Service, private personService: PersonService) {}

    ngOnInit() {
        this.personService.getGraph(parseInt(localStorage.getItem('id')))
          .subscribe(this.renderGraph.bind(this));
        
    }

    renderGraph(person) {
        this.renderFollowing(person);

        this.graph = this.d3Service.getGraph(
            this.nodes,
            this.links, 
            { width: 720, height: 480 });

        this.graph.initSimulation({ width: 720, height: 480 });
    }

    renderFollowing(me) {
        let queue = [() => ({ current: me, parent: null })];
        let visited = [me.id];
        
        while (queue.length > 0) {
            const { current, parent } = queue.shift()();

            const node = new Node(current.id, `${current.id}: ${current.handle}`);
            this.nodes.push(node);

            if (parent) {
                this.links.push(new Link(parent, node));
            }

            current.following.forEach(f => {
                if (visited.indexOf(f.id) < 0) {
                    visited.push(f.id);
                    queue.push(() => ({ current: f, parent: node }));
                }
            });
        }
    }

    private _options: { width, height } = { width: 800, height: 600 };

    get options() {
        return this._options = {
            width: window.innerWidth,
            height: window.innerHeight
        }
    }
}