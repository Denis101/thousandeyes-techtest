import * as d3 from 'd3';

export class Node implements d3.SimulationNodeDatum {
    index?: number;
    x?: number;
    y?: number;
    vx?: number;
    vy?: number;
    fx?: number | null;
    fy?: number | null;
    
    id: string;
    text: string;
    delta: number;
    
    constructor(id: string, text: string) {
        this.id = id;
        this.text = text;
        this.delta = Math.round(128 + ((parseInt(this.id) / 10) * 127));
    }

    get color() {
        return `rgb(80, ${this.delta}, ${this.delta})`;
    }
}