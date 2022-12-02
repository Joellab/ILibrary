import{P as u,Q as g,R as m,p as t,S as y,U as N,W as _,X as h,Y as P,Z as T,$ as r,a0 as A,a1 as L,a2 as R,a3 as I,a4 as w,a5 as X,a6 as Y,a7 as D,a8 as M,a9 as c,A as Q}from"./index.03179c4f.js";const W=u({name:"VListItemAction",props:{start:Boolean,end:Boolean,...g()},setup(e,a){let{slots:i}=a;return m(()=>t(e.tag,{class:["v-list-item-action",{"v-list-item-action--start":e.start,"v-list-item-action--end":e.end}]},i)),{}}});const Z=u({name:"VBadge",inheritAttrs:!1,props:{bordered:Boolean,color:String,content:[Number,String],dot:Boolean,floating:Boolean,icon:y,inline:Boolean,label:{type:String,default:"$vuetify.badge"},max:[Number,String],modelValue:{type:Boolean,default:!0},offsetX:[Number,String],offsetY:[Number,String],textColor:String,...N({location:"top end"}),..._(),...g(),...h(),...P({transition:"scale-rotate-transition"})},setup(e,a){const{backgroundColorClasses:i,backgroundColorStyles:b}=T(r(e,"color")),{roundedClasses:v}=A(e),{t:f}=L(),{textColorClasses:C,textColorStyles:S}=R(r(e,"textColor")),{themeClasses:x}=I(),{locationStyles:B}=w(e,!0,o=>{var n,l;return(e.floating?e.dot?2:4:e.dot?8:12)+(["top","bottom"].includes(o)?+((n=e.offsetY)!=null?n:0):["left","right"].includes(o)?+((l=e.offsetX)!=null?l:0):0)});return m(()=>{var o,d,n,l;const s=Number(e.content),V=!e.max||isNaN(s)?e.content:s<=e.max?s:`${e.max}+`,[$,k]=X(a.attrs,["aria-atomic","aria-label","aria-live","role","title"]);return t(e.tag,c({class:["v-badge",{"v-badge--bordered":e.bordered,"v-badge--dot":e.dot,"v-badge--floating":e.floating,"v-badge--inline":e.inline}]},k),{default:()=>[t("div",{class:"v-badge__wrapper"},[(o=(d=a.slots).default)==null?void 0:o.call(d),t(Y,{transition:e.transition},{default:()=>[D(t("span",c({class:["v-badge__badge",x.value,i.value,v.value,C.value],style:[b.value,S.value,e.inline?{}:B.value],"aria-atomic":"true","aria-label":f(e.label,s),"aria-live":"polite",role:"status"},$),[e.dot?void 0:a.slots.badge?(n=(l=a.slots).badge)==null?void 0:n.call(l):e.icon?t(Q,{icon:e.icon},null):V]),[[M,e.modelValue]])]})])]})}),{}}});export{W as V,Z as a};