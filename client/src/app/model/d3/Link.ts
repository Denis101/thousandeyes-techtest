import { Node } from "./Node";

export class Link implements d3.SimulationLinkDatum<Node> {
    index?: number;

    source: Node | string | number;
    target: Node | string | number;

    constructor(source: Node | string | number, target: Node | string | number) {
        this.source = source;
        this.target = target;
    }
}