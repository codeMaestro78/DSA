/** Convert every Markdown page with an installed Marked parser.
 * Usage: node check_render.mjs --marked /path/to/marked.esm.js --output /tmp/render-dir
 * No downloads or browser. Mermaid fences remain text: this is not SVG rendering.
 */
import fs from 'node:fs';
import path from 'node:path';
import {pathToFileURL,fileURLToPath} from 'node:url';
const args=process.argv.slice(2);
const value=key=>{const i=args.indexOf(key);return i<0?null:args[i+1]};
const modulePath=value('--marked');const output=value('--output');
if(!modulePath||!output)throw new Error('Require --marked installed parser path and --output directory');
const {marked}=await import(pathToFileURL(path.resolve(modulePath)).href);
const root=path.resolve(path.dirname(fileURLToPath(import.meta.url)),'..');
const walk=folder=>fs.readdirSync(folder,{withFileTypes:true}).flatMap(e=>e.isDirectory()?walk(path.join(folder,e.name)):e.name.endsWith('.md')?[path.join(folder,e.name)]:[]);
const files=walk(root).sort();let disclosures=0,tables=0,diagrams=0;const errors=[];
for(const file of files){
 const source=fs.readFileSync(file,'utf8');const tokens=marked.lexer(source,{gfm:true});
 const html=marked.parser(tokens,{gfm:true});const rel=path.relative(root,file);
 const headings=[...html.matchAll(/<h1(?:\s[^>]*)?>/g)];
 if(headings.length!==1)errors.push(`${rel}: rendered top-level heading count ${headings.length}`);
 const starts=[...html.matchAll(/<details(?:\s[^>]*)?>/g)].length;
 const ends=[...html.matchAll(/<\/details>/g)].length;
 if(starts!==ends)errors.push(`${rel}: rendered disclosure imbalance`);
 disclosures+=starts;tables+=(html.match(/<table>/g)||[]).length;
 diagrams+=(source.match(/^```mermaid\s*$/gm)||[]).length;
 const destination=path.join(path.resolve(output),rel.replace(/\.md$/,'.html'));
 fs.mkdirSync(path.dirname(destination),{recursive:true});
 // Original relative Markdown links remain documentation paths; these artifacts
 // are conversion inspection material, not a published fully routed website.
 fs.writeFileSync(destination,'<!doctype html><meta charset="utf-8"><title>'+rel.replaceAll('&','&amp;').replaceAll('<','&lt;')+'</title><main>'+html+'</main>');
}
const result={scope:'Static GFM conversion only; no browser, Mermaid parse/SVG or CSS inspection',files:files.length,disclosures,tables,mermaid_fences:diagrams,errors};
fs.writeFileSync(path.join(path.resolve(output),'result.json'),JSON.stringify(result,null,2)+'\n');
console.log(JSON.stringify(result));if(errors.length)process.exitCode=1;
