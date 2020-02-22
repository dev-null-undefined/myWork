using System;
using System.Collections.Generic;
using System.Text;

namespace Chess
{
    interface IFigure
    {
        public Point position
        {
            get;
        }
        public char displayName
        {
            get;
        }
        public bool dead
        {
            get;
        }
        public bool move(Point finalPosition);
        public bool canMove(Point finalPosition);
    }
}
