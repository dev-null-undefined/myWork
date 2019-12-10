class Particle{
    constructor(amount){
        this.amount=360/amount;
        this.Roff=0;
        this.Goff=100000;
        this.Boff=1000000000;
        this.pos=createVector(width/2,height/2);
        this.rays=[];
        for(let a = 0;a<360;a+=amount){
            this.rays.push(new Ray(this.pos,radians(a)));
        }
    }
}