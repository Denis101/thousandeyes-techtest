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
    _color: string;
    
    constructor(id: string, text: string) {
        this.id = id;
        this.text = text;
        
        const rand = (max, seed) => {
            const x = Math.sin(seed++) * 10000;
            return Math.floor((x - Math.floor(x)) * Math.floor(max));
        }

        const delta = parseInt(this.id) / 10;

        const r = Math.round(rand(255, delta) + (1 / delta));
        const g = Math.round(63 + rand(192, delta) + delta);
        const b = Math.round(127 + rand(128, delta) + delta);
        this._color = `rgb(${r}, ${g}, ${b})`;
    }

    get color() {
        return this._color;
    }
}