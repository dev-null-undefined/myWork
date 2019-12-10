using System;
using System.Collections.Generic;
using System.Text;
using System.Linq;

namespace DialogSystem
{
    class Dialog
    {
        protected string _dialogText;
        protected Dictionary<string, Dialog> _options;
        protected string _basedOn;
        protected float _basedOnValue;

        public Dialog(string dialogText, Dictionary<string, Dialog> options)
        {
            _dialogText = dialogText;
            _options = options;
        }

        public void addOption(string option,Dialog resault)
        {
           _options.Add(option, resault);
        }

        public void removeOption(string option)
        {
            _options.Remove(option);
        }
        
        public Dialog choseOption(string option)
        {
            return _options[option];
        }

        public Dictionary<string,bool> getOptions(GetAtribute o)
        {
            Dictionary<string, bool> returnDict = new Dictionary<string, bool>();
            foreach(string s in _options.Keys.ToArray())
            {
                if (_options[s]._basedOn.Equals("") || _options[s]._basedOn.Equals("nothing"))
                {
                    returnDict.Add(s, true);
                }
                else
                {
                    switch (_options[s]._basedOn)
                    {
                        case ">":
                            if (o.getAtribute(_basedOn)>_basedOnValue)
                            {
                                returnDict.Add(s, true);
                            }
                            break;
                        case "<":
                            if (o.getAtribute(_basedOn) < _basedOnValue)
                            {
                                returnDict.Add(s, true);
                            }
                            break;
                        case "=":
                            if (o.getAtribute(_basedOn) == _basedOnValue)
                            {
                                returnDict.Add(s, true);
                            }
                            break;
                    }
                }
            }
            return returnDict;
        }
    }
}
