import { Injectable, ElementRef } from '@angular/core';
import * as d3 from 'd3';
import { ForceDirectedGraph } from '../model/d3/ForceDirectedGraph';
import { Node } from '../model/d3/Node';
import { Link } from '../model/d3/Link';

@Injectable()
export class D3Service {
    constructor() {}

    applyZoomableBehaviour(svgElement, containerElement) {
        let svg = d3.select(svgElement);
        let container = d3.select(containerElement);

        let zoom = d3.zoom().on('zoom', () => {
            const transform = d3.event.transform;
            container.attr('transform', `translate(${transform.x}, ${transform.y}) scale(${transform.k})`);
        });

        svg.call(zoom);
    }

    applyDraggableBehaviour(element, node: Node, graph: ForceDirectedGraph) {
        const d3element = d3.select(element);

        const started = () => {
            d3.event.sourceEvent.stopPropagation();

            if (!d3.event.active) {
                graph.simulation.alphaTarget(0.3).restart();
            }

            d3.event.on('drag', () => {
                node.fx = d3.event.x;
                node.fy = d3.event.y;
            }).on('end', () => {
                if (!d3.event.active) {
                    graph.simulation.alphaTarget(0);
                }

                node.fx = null;
                node.fy = null;
            });
        };

        d3element.call(d3.drag().on('start', started));
    }

    getGraph(nodes: Node[], links: Link[], options: { width, height }) {
        return new ForceDirectedGraph(nodes, links, options);
    }
}