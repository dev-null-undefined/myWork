using System;
using System.Collections.Generic;
using System.Text;

namespace Chess
{
    class Tower:IFigure
    {
        public char displayName => 'X';
        private Point _position;
        private bool _dead;
        public Point position
        {
            get => _position;
        }
        public bool dead
        {
            get => _dead;
        }

        private Table currentGame;
        /// <summary>
        /// Constructor for Tower figure.
        /// </summary>
        /// <param name="startPosition">
        ///  Start position is Point that has both X and Y bigger or equel to 0 and smaller then size of table.
        /// </param>
        /// <param name="currentGame">
        /// Table where the figure is placed.
        /// </param>
        /// <exception cref="System.NotSupportedException">
        /// Throws where start position is out of the table.
        /// </exception>
        Tower(Point startPosition,Table currentGame)
        {
            if (startPosition.X < 0 || startPosition.X > currentGame.sizeOfTable || startPosition.Y < 0 || startPosition.Y > currentGame.sizeOfTable)
            {
                throw new NotSupportedException("[" + startPosition.X + "," + startPosition.Y + "] - start position can not be out of the game table or smaller then 0.");
            }
            _position = startPosition;
            _dead = false;
        }

        /// <summary>
        /// Move function used to move figures on game table.
        /// </summary>
        /// <param name="finalPosition">
        /// Point final position where you want to move the figure, must be valid location on board.
        /// </param>
        /// <returns>
        /// true if the movement has been done, else false which means that move is not posible.
        /// </returns>
        public bool move(Point finalPosition)
        {
            if (canMove(finalPosition))
            {
                _position = finalPosition;
                return true;
            }
            return false;
            throw new NotImplementedException();
        }
        /// <summary>
        /// return bool if this figure can move to the finalPosition, does not move the figure.
        /// </summary>
        /// <param name="finalPosition">
        /// Point final position where you want to move the figure, must be valid location on board.
        /// </param>
        /// <returns>
        /// true if the movement is posible, else false which means that move is not posible.
        /// </returns>
        public bool canMove(Point finalPosition)
        {
            throw new NotImplementedException();
        }
    }
}
