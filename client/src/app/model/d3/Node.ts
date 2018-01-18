import * as d3 from 'd3';

export class Node implements d3.SimulationNodeDatum {
    index?: number;


    id: string;
    
    constructor(id: string) {
        this.id = id;
    }
}