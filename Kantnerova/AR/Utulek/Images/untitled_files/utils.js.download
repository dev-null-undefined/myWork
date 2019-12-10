if(!Array.prototype.findIndex){Object.defineProperty(Array.prototype,'findIndex',{value:function(predicate){if(this==null){throw new TypeError('"this" is null or not defined');}
var o=Object(this);var len=o.length>>>0;if(typeof predicate!=='function'){throw new TypeError('predicate must be a function');}
var thisArg=arguments[1];var k=0;while(k<len){var kValue=o[k];if(predicate.call(thisArg,kValue,k,o)){return k;}
k++;}
return-1;},configurable:true,writable:true});}
function getWeightedRandom(elements){var cumulativeArray=[];var cumulativeKeys=[];var cumulativeProbability=0;Object.keys(elements).map(function(element){cumulativeProbability+=100*elements[element];cumulativeArray.push(cumulativeProbability);cumulativeKeys.push(element);});if(cumulativeProbability!==100){console.error('Probabilities received and multiplied by 100 in getWeightedRandom do not sum to 100 but to ',cumulativeProbability)}
var randomPercentage=100*Math.random();var resultIndex=cumulativeArray.findIndex(function(cumulativePercentage,index){return randomPercentage<cumulativePercentage;});return cumulativeKeys[resultIndex];}
function loadScript(url,callback){var head=document.getElementsByTagName('head')[0];var script=document.createElement('script');script.type='text/javascript';script.src=url;script.async=true;script.onreadystatechange=callback;script.onload=callback;head.appendChild(script);}