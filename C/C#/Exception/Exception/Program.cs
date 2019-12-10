using System;
using System.Collections.Generic;

namespace Exception
{
    class Program
    {
        static void Main(string[] args)
        {
            List<int> array1 = new List<int>{ 1, 3, 5, 7, 9 };
            Console.WriteLine(array1.Reduce(concat,""));
            Console.WriteLine("------------------");
            List<int> p = array1.FilterArrayReplace((int x, int index) => { return x < 5;});
            Console.WriteLine(p.Reduce(concat, ""));
            Console.WriteLine("------------------");
            Console.WriteLine(array1.arrayToString());
            Console.WriteLine("------------------");
            //level1();
        }
        static string concat(string s,int x,int index,List<int> neco)
        {
            return s + ", cislo s indexem[" + index + "] je:" + x;
        }
    }

    public static class Extentions
    {
        public static string arrayToString<T>(this List<T> array)
        {
            string s = "{";
            for (int index = 0; index < array.Count; index++)
            {
                s += "[" + index + "]=>(" + array[index] + "),";
            }
            s += "}";
            return s;
        }
        public static B Reduce<T,B>(this List<T> array,Func<B,T,int,List<T>,B> reduceFunc,B initValue)
        {
            for(int index = 0;index<array.Count;index++)
            {
                 initValue = reduceFunc(initValue, array[index], index, array);
            }
            return initValue;
        }
        public static B Reduce<T, B>(this List<T> array, Func<B, T, int, List<T>, B> reduceFunc)
        {
            B initValue = default(B);
            for (int index = 0; index < array.Count; index++)
            {
                initValue = reduceFunc(initValue, array[index], index, array);
            }
            return initValue;
        }
        public static B Reduce<T, B>(this List<T> array, Func<B, T, int, B> reduceFunc, B initValue)
        {
            for (int index = 0; index < array.Count; index++)
            {
                initValue = reduceFunc(initValue, array[index], index);
            }
            return initValue;
        }
        public static B Reduce<T, B>(this List<T> array, Func<B, T, int, B> reduceFunc)
        {
            B initValue = default(B);
            for (int index = 0; index < array.Count; index++)
            {
                initValue = reduceFunc(initValue, array[index], index);
            }
            return initValue;
        }
        public static B Reduce<T, B>(this List<T> array, Func<B, T, B> reduceFunc, B initValue)
        {
            for (int index = 0; index < array.Count; index++)
            {
                initValue = reduceFunc(initValue, array[index]);
            }
            return initValue;
        }
        public static B Reduce<T, B>(this List<T> array, Func<B, T, B> reduceFunc)
        {
            B initValue = default(B);
            for (int index = 0; index < array.Count; index++)
            {
                initValue = reduceFunc(initValue, array[index]);
            }
            return initValue;
        }
        public static B Reduce<T, B>(this List<T> array, Func<B, T, List<T>, B> reduceFunc, B initValue)
        {
            for (int index = 0; index < array.Count; index++)
            {
                initValue = reduceFunc(initValue, array[index], array);
            }
            return initValue;
        }
        public static B Reduce<T, B>(this List<T> array, Func<B, T, List<T>, B> reduceFunc)
        {
            B initValue = default(B);
            for (int index = 0; index < array.Count; index++)
            {
                initValue = reduceFunc(initValue, array[index], array);
            }
            return initValue;
        }
        public static List<T> FilterArray<T>(this List<T> array, Func<T, int, bool> filterFunction)
        {
            List<T> newList = new List<T>();
            int index = 0;
            foreach (T element in array)
            {
                if (filterFunction(element, index))
                {
                    newList.Add(element);
                }
                index++;
            }
            return newList;
        }
        public static List<T> FilterArray<T>(this List<T> array, Func<T, bool> filterFunction)
        {
            List<T> newList = new List<T>();
            foreach (T element in array)
            {
                if (filterFunction(element))
                {
                    newList.Add(element);
                }
            }
            return newList;
        }
        public static List<T> FilterArrayReplace<T>(this List<T> array, Func<T, int, bool> filterFunction)
        {
            List<T> newList = new List<T>();
            int index = 0;
            foreach (T element in array)
            {
                if (filterFunction(element, index))
                {
                    newList.Add(element);
                }
                index++;
            }
            array.Clear();
            foreach (T element in newList)
            {
                array.Add(element);
            }
            return newList;
        }
        public static List<T> FilterArrayReplace<T>(this List<T> array, Func<T, bool> filterFunction)
        {
            List<T> newList = new List<T>();
            foreach (T element in array)
            {
                if (filterFunction(element))
                {
                    newList.Add(element);
                }
            }
            array.Clear();
            foreach (T element in newList)
            {
                array.Add(element);
            }
            return newList;
        }
        public static T Find<T>(this List<T> array, Func<T,int, bool> filterFunction)
        {
            List<T> newList = new List<T>();
            int index = 0;
            foreach (T element in array)
            {
                if (filterFunction(element,index))
                {
                    return element;
                }
                index++;
            }
            return default(T);
        }
        public static T Find<T>(this List<T> array, Func<T, bool> filterFunction)
        {
            List<T> newList = new List<T>();
            int index = 0;
            foreach (T element in array)
            {
                if (filterFunction(element))
                {
                    return element;
                }
                index++;
            }
            return default(T);
        }
    }
}
