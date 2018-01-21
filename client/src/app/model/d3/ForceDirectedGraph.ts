import { EventEmitter } from '@angular/core';
import * as d3 from 'd3';
import { Node } from './Node';
import { Link } from './Link';

const FORCES = {
    LINKS: 1 / 50,
    COLLISION: 1,
    CHARGE: -1
}

export class ForceDirectedGraph {
    public ticker: EventEmitter<d3.Simulation<Node, Link>> = new EventEmitter();
    public simulation: d3.Simulation<any, any>;

    public nodes: Node[] = [];
    public links: Link[] = [];

    constructor(nodes: Node[], links: Link[], options: { width, height }) {
        this.nodes = nodes;
        this.links = links;

        this.initSimulation(options);
    }

    connectNodes(source, target) {
        let link;
    
        if (!this.nodes[source] || !this.nodes[target]) {
          throw new Error('One of the nodes does not exist');
        }
    
        link = new Link(source, target);
        this.simulation.stop();
        this.links.push(link);
        this.simulation.alphaTarget(0.3).restart();
    
        this.initLinks();
      }

    initNodes() {
        this.simulation.nodes(this.nodes);
    }

    initLinks() {
        this.simulation.force('links',
            d3.forceLink(this.links).strength(FORCES.LINKS));
    }

    initSimulation(options: { width, height}) {
        if (!this.simulation) {
            const ticker = this.ticker;

            this.simulation = d3.forceSimulation()
                .force('charge', d3.forceManyBody().strength(FORCES.CHARGE))
                .force('collide', d3.forceCollide().strength(FORCES.COLLISION)
                    .radius(d => d['r'] + 5).iterations(2));

            this.simulation.on('tick', () => {
                ticker.emit(this.simulation);
            });

            this.initNodes();
            this.initLinks();
        }

        this.simulation.force('centers', d3.forceCenter(options.width / 2, options.height / 2));
        this.simulation.restart();
    }
}