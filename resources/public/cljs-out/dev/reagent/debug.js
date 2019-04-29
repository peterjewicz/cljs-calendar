// Compiled by ClojureScript 1.10.339 {}
goog.provide('reagent.debug');
goog.require('cljs.core');
reagent.debug.has_console = (typeof console !== 'undefined');
reagent.debug.tracking = false;
if((typeof reagent !== 'undefined') && (typeof reagent.debug !== 'undefined') && (typeof reagent.debug.warnings !== 'undefined')){
} else {
reagent.debug.warnings = cljs.core.atom.call(null,null);
}
if((typeof reagent !== 'undefined') && (typeof reagent.debug !== 'undefined') && (typeof reagent.debug.track_console !== 'undefined')){
} else {
reagent.debug.track_console = (function (){var o = ({});
o.warn = ((function (o){
return (function() { 
var G__8263__delegate = function (args){
return cljs.core.swap_BANG_.call(null,reagent.debug.warnings,cljs.core.update_in,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"warn","warn",-436710552)], null),cljs.core.conj,cljs.core.apply.call(null,cljs.core.str,args));
};
var G__8263 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__8264__i = 0, G__8264__a = new Array(arguments.length -  0);
while (G__8264__i < G__8264__a.length) {G__8264__a[G__8264__i] = arguments[G__8264__i + 0]; ++G__8264__i;}
  args = new cljs.core.IndexedSeq(G__8264__a,0,null);
} 
return G__8263__delegate.call(this,args);};
G__8263.cljs$lang$maxFixedArity = 0;
G__8263.cljs$lang$applyTo = (function (arglist__8265){
var args = cljs.core.seq(arglist__8265);
return G__8263__delegate(args);
});
G__8263.cljs$core$IFn$_invoke$arity$variadic = G__8263__delegate;
return G__8263;
})()
;})(o))
;

o.error = ((function (o){
return (function() { 
var G__8266__delegate = function (args){
return cljs.core.swap_BANG_.call(null,reagent.debug.warnings,cljs.core.update_in,new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"error","error",-978969032)], null),cljs.core.conj,cljs.core.apply.call(null,cljs.core.str,args));
};
var G__8266 = function (var_args){
var args = null;
if (arguments.length > 0) {
var G__8267__i = 0, G__8267__a = new Array(arguments.length -  0);
while (G__8267__i < G__8267__a.length) {G__8267__a[G__8267__i] = arguments[G__8267__i + 0]; ++G__8267__i;}
  args = new cljs.core.IndexedSeq(G__8267__a,0,null);
} 
return G__8266__delegate.call(this,args);};
G__8266.cljs$lang$maxFixedArity = 0;
G__8266.cljs$lang$applyTo = (function (arglist__8268){
var args = cljs.core.seq(arglist__8268);
return G__8266__delegate(args);
});
G__8266.cljs$core$IFn$_invoke$arity$variadic = G__8266__delegate;
return G__8266;
})()
;})(o))
;

return o;
})();
}
reagent.debug.track_warnings = (function reagent$debug$track_warnings(f){
reagent.debug.tracking = true;

cljs.core.reset_BANG_.call(null,reagent.debug.warnings,null);

f.call(null);

var warns = cljs.core.deref.call(null,reagent.debug.warnings);
cljs.core.reset_BANG_.call(null,reagent.debug.warnings,null);

reagent.debug.tracking = false;

return warns;
});

//# sourceMappingURL=debug.js.map
