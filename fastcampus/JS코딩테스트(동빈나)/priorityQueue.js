/*
class PriorityQueue {
    constructor(comparator) {
        this._items = [];
        this._comparator = comparator || PriorityQueue.comparator;
    }

    enq(item) {
        this._items.push(item);

        let current = this.size() - 1;
        while(current > 0) {
            let parent = Math.floor((current - 1) / 2);
            
            if(this.compareTo(parent, current) <= 0) break;

            this.swap(parent, current);
            current = parent; 
        }
    }

    deq() {
        const first = this.peek();
        let last = this._items.pop();
        
        if(this.isEmpty()) return first;

        this._items[0] = last;
        let current = 0;
        
        while(current < this.size() - 1) {
            let position = current;
            let left = (2 * current) + 1;
            let right = (2 * current) + 2;

            if(right >= this.size()) {
                if (this.compareTo(position, left) >= 0) {
                    position = left;
                }
            } else {
                if(this.compareTo(left, right) <= 0) {
                    if (this.compareTo(position, left) >= 0) {
                        position = left;
                    }
                } else {
                    if (this.compareTo(position, right) >= 0) {
                        position = right;
                    }
                }
            }

            if (position === current) break;

            this.swap(current, position);
            current = position;
        }
        
        return first;
    }

    isEmpty() {
        return this._items.length === 0;
    }

    peek() {
        if(this.isEmpty()) throw new Error('PriorityQueue is Empty');

        return this._items[0];
    }

    size() {
        return this._items.length;
    }

    swap(a, b) {
        const temp = this._items[a];
        this._items[a] = this._items[b];
        this._items[b] = temp;
    }

    compareTo(a, b) {
        return this._comparator(this._items[a], this._items[b]);
    }

    static comparator(a, b) {
        if(typeof a === 'number' && typeof b === 'number') {
            return a - b;
        } else {
            a = a.toString();
            b = b.toString();

            if(a === b) return 0;

            return (a > b) ? 1 : -1;
        }
    }
}
*/

class PriorityQueue {
    constructor(comparator) {
        this._items = [];
        this._comparator = comparator;
    }

    enq(item) {
        this._items.push(item);

        let current = this.size() - 1;
        while(current > 0) {
            let parent = Math.floor((current - 1) / 2);
            
            if(this.compareTo(parent, current) <= 0) break;

            this.swap(parent, current);
            current = parent; 
        }
    }

    deq() {
        const first = this.peek();
        let last = this._items.pop();
        
        if(this.isEmpty()) return first;

        this._items[0] = last;
        let index = 0;
        let size = this.size();
        
        while(index < size) {
            let target = index;
            let left = (2 * index) + 1;
            let right = left + 1;
            
            if(left < size && this.compareTo(target, left) >= 0) {
               target = left;
            }
               
            if(right < size && this.compareTo(target, right) >= 0) {
               target = right; 
            }
            
            if(index === target) break;
            
            this.swap(index, target);
            index = target;
        }
        
        return first;
    }

    isEmpty() {
        return this.size() === 0;
    }

    peek() {
        if(this.isEmpty()) throw new Error('PriorityQueue is Empty');

        return this._items[0];
    }

    size() {
        return this._items.length;
    }

    swap(a, b) {
        const temp = this._items[a];
        this._items[a] = this._items[b];
        this._items[b] = temp;
    }

    compareTo(a, b) {
        return this._comparator(this._items[a], this._items[b]);
    }

    forEach(callBack) {
        if(callBack === undefined || callBack === null) return;

        return this._items.forEach(callBack);
    }
}


function dijkstra(start) {
    let pq = new PriorityQueue((a, b) => a[1] - b[1]); 
    
    pq.enq([start, 0]); // 시작노드, 비용
    distance[start] = 0;
    
    while(pq.size() > 0) {
        let [now, cost] = pq.deq();
        
        if(distance[now] < cost) continue;
        
        for(let [nextNode, nextDistance] of graph[now]) {
            let newCost = distance[now] + nextDistance;
            
            if(distance[nextNode] > newCost) {
                distance[nextNode] = newCost;
                pq.enq([nextNode, newCost]);
            }
        }
    }
}


//const fs = require('fs');
//const input = fs.readFileSync('/dev/stdin').toString().split('\n');

const [v, e] = [5, 6];
//const k = Number(input[1]); 
/*
const graph = [];
for(let i = 0; i <= v; i++) graph.push([]);


const lines = input.slice(2, e + 2).map(line => line.split(' ').map(Number));
for(let [f, t, c] of lines) {
   graph[f].push([t, c]);
}


const graph = [
    [],
    [ [ 2, 2 ], [ 3, 3 ] ],
    [ [ 3, 4 ], [ 4, 5 ] ],
    [ [ 4, 6 ] ],
    [],
    [ [ 1, 1 ] ]
  ];


const INF = 1e9;
const distance = new Array(v + 1).fill(INF);

console.log(graph);

dijkstra(1);

console.log(distance.map(value => (value === INF ? "INF" : value)).slice(1).join("\n"));

*/



const pq = new PriorityQueue((a, b) => a - b);

pq.enq(15);
pq.enq(10);
pq.enq(8);
pq.enq(5);
pq.enq(4);
pq.enq(20);
pq.forEach(item => console.log(item)); // 4 5 10 15 8 20

console.log("========");
pq.deq(); // 4
console.log("========");
pq.forEach(item => console.log(item)); // 5 8 10 15 20
