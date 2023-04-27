class Queue {
    constructor() {
        this.items = {};
        this.headIndex = 0;
        this.tailIndex = 0;
    }

    enqueue(item) {
        this.items[this.tailIndex++] = item;
    }

    dequeue() {
        const item = this.items[this.headIndex];
        delete this.items[this.headIndex];
        this.headIndex++;
        return item;
    }
    
    peek() {
        return this.items[this.headIndex];
    }

    length() {
        return this.tailIndex - this.headIndex;
    }

    printAll() {
        for(let i = this.headIndex; i < this.tailIndex; i++) {
            console.log(this.items[i]);
        }
    }
}

function testQueue() {
    const queue = new Queue();

    queue.enqueue(5);
    queue.enqueue(2);
    queue.enqueue(3);
    queue.enqueue(7);
    queue.dequeue(); // 5
    queue.enqueue(1);
    queue.enqueue(4); 
    queue.dequeue(); // 2

    queue.printAll(); // 3 7 1 4
}

testQueue();