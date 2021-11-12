package seedu.address.commons.util;

import java.util.HashMap;

import javafx.scene.paint.Color;

public class LangColorUtil {
    private static HashMap<String, Color> colorMap = new HashMap<>();

    static {
        /* Retrieved using data found here: https://github.com/ozh/github-colors/blob/master/colors.json */
        colorMap.put("1C Enterprise", Color.rgb(129, 76, 204));
        colorMap.put("4D", Color.rgb(0, 66, 137));
        colorMap.put("ABAP", Color.rgb(232, 39, 75));
        colorMap.put("ABAP CDS", Color.rgb(85, 94, 37));
        colorMap.put("ActionScript", Color.rgb(136, 43, 15));
        colorMap.put("Ada", Color.rgb(2, 248, 140));
        colorMap.put("Adobe Font Metrics", Color.rgb(250, 15, 0));
        colorMap.put("Agda", Color.rgb(49, 86, 101));
        colorMap.put("AGS Script", Color.rgb(185, 217, 255));
        colorMap.put("AIDL", Color.rgb(52, 235, 107));
        colorMap.put("AL", Color.rgb(58, 162, 181));
        colorMap.put("Alloy", Color.rgb(100, 200, 0));
        colorMap.put("Alpine Abuild", Color.rgb(13, 89, 127));
        colorMap.put("Altium Designer", Color.rgb(168, 150, 99));
        colorMap.put("AMPL", Color.rgb(230, 239, 187));
        colorMap.put("AngelScript", Color.rgb(199, 215, 220));
        colorMap.put("Ant Build System", Color.rgb(169, 21, 126));
        colorMap.put("ANTLR", Color.rgb(157, 195, 255));
        colorMap.put("ApacheConf", Color.rgb(209, 33, 39));
        colorMap.put("Apex", Color.rgb(23, 151, 192));
        colorMap.put("API Blueprint", Color.rgb(42, 204, 168));
        colorMap.put("APL", Color.rgb(90, 129, 100));
        colorMap.put("Apollo Guidance Computer", Color.rgb(11, 61, 145));
        colorMap.put("AppleScript", Color.rgb(16, 31, 31));
        colorMap.put("Arc", Color.rgb(170, 42, 254));
        colorMap.put("AsciiDoc", Color.rgb(115, 160, 197));
        colorMap.put("ASP.NET", Color.rgb(148, 0, 255));
        colorMap.put("AspectJ", Color.rgb(169, 87, 176));
        colorMap.put("Assembly", Color.rgb(110, 76, 19));
        colorMap.put("Astro", Color.rgb(255, 90, 3));
        colorMap.put("Asymptote", Color.rgb(255, 0, 0));
        colorMap.put("ATS", Color.rgb(26, 198, 32));
        colorMap.put("Augeas", Color.rgb(156, 193, 52));
        colorMap.put("AutoHotkey", Color.rgb(101, 148, 185));
        colorMap.put("AutoIt", Color.rgb(28, 53, 82));
        colorMap.put("Avro IDL", Color.rgb(0, 64, 255));
        colorMap.put("Awk", Color.rgb(195, 14, 155));
        colorMap.put("Ballerina", Color.rgb(255, 80, 0));
        colorMap.put("BASIC", Color.rgb(255, 0, 0));
        colorMap.put("Batchfile", Color.rgb(193, 241, 46));
        colorMap.put("Beef", Color.rgb(165, 47, 78));
        colorMap.put("BibTeX", Color.rgb(119, 136, 153));
        colorMap.put("Bicep", Color.rgb(81, 154, 186));
        colorMap.put("Bison", Color.rgb(106, 70, 63));
        colorMap.put("BitBake", Color.rgb(0, 188, 228));
        colorMap.put("Blade", Color.rgb(247, 82, 63));
        colorMap.put("BlitzBasic", Color.rgb(0, 255, 174));
        colorMap.put("BlitzMax", Color.rgb(205, 100, 0));
        colorMap.put("Bluespec", Color.rgb(18, 34, 60));
        colorMap.put("Boo", Color.rgb(212, 190, 193));
        colorMap.put("Boogie", Color.rgb(200, 15, 160));
        colorMap.put("Brainfuck", Color.rgb(47, 37, 48));
        colorMap.put("Brightscript", Color.rgb(102, 45, 145));
        colorMap.put("Browserslist", Color.rgb(255, 213, 57));
        colorMap.put("C", Color.rgb(85, 85, 85));
        colorMap.put("C#", Color.rgb(23, 134, 0));
        colorMap.put("C++", Color.rgb(243, 75, 125));
        colorMap.put("Cabal Config", Color.rgb(72, 52, 101));
        colorMap.put("Cap'n Proto", Color.rgb(196, 39, 39));
        colorMap.put("Ceylon", Color.rgb(223, 165, 53));
        colorMap.put("Chapel", Color.rgb(141, 198, 63));
        colorMap.put("ChucK", Color.rgb(63, 128, 0));
        colorMap.put("Cirru", Color.rgb(204, 204, 255));
        colorMap.put("Clarion", Color.rgb(219, 144, 30));
        colorMap.put("Classic ASP", Color.rgb(106, 64, 253));
        colorMap.put("Clean", Color.rgb(63, 133, 175));
        colorMap.put("Click", Color.rgb(228, 230, 243));
        colorMap.put("CLIPS", Color.rgb(0, 163, 0));
        colorMap.put("Clojure", Color.rgb(219, 88, 85));
        colorMap.put("Closure Templates", Color.rgb(13, 148, 143));
        colorMap.put("Cloud Firestore Security Rules", Color.rgb(255, 160, 0));
        colorMap.put("CMake", Color.rgb(218, 52, 52));
        colorMap.put("CodeQL", Color.rgb(20, 15, 70));
        colorMap.put("CoffeeScript", Color.rgb(36, 71, 118));
        colorMap.put("ColdFusion", Color.rgb(237, 44, 214));
        colorMap.put("ColdFusion CFC", Color.rgb(237, 44, 214));
        colorMap.put("COLLADA", Color.rgb(241, 164, 43));
        colorMap.put("Common Lisp", Color.rgb(63, 182, 139));
        colorMap.put("Common Workflow Language", Color.rgb(181, 49, 76));
        colorMap.put("Component Pascal", Color.rgb(176, 206, 78));
        colorMap.put("Coq", Color.rgb(208, 182, 140));
        colorMap.put("Crystal", Color.rgb(0, 1, 0));
        colorMap.put("CSON", Color.rgb(36, 71, 118));
        colorMap.put("Csound", Color.rgb(26, 26, 26));
        colorMap.put("Csound Document", Color.rgb(26, 26, 26));
        colorMap.put("Csound Score", Color.rgb(26, 26, 26));
        colorMap.put("CSS", Color.rgb(86, 61, 124));
        colorMap.put("CSV", Color.rgb(35, 115, 70));
        colorMap.put("Cuda", Color.rgb(58, 78, 58));
        colorMap.put("CUE", Color.rgb(88, 134, 225));
        colorMap.put("CWeb", Color.rgb(0, 0, 122));
        colorMap.put("Cython", Color.rgb(254, 223, 91));
        colorMap.put("D", Color.rgb(186, 89, 94));
        colorMap.put("Dafny", Color.rgb(255, 236, 37));
        colorMap.put("Darcs Patch", Color.rgb(142, 255, 35));
        colorMap.put("Dart", Color.rgb(0, 180, 171));
        colorMap.put("DataWeave", Color.rgb(0, 58, 82));
        colorMap.put("Dhall", Color.rgb(223, 175, 255));
        colorMap.put("DirectX 3D File", Color.rgb(170, 206, 96));
        colorMap.put("DM", Color.rgb(68, 114, 101));
        colorMap.put("Dockerfile", Color.rgb(56, 77, 84));
        colorMap.put("Dogescript", Color.rgb(204, 167, 96));
        colorMap.put("Dylan", Color.rgb(108, 97, 110));
        colorMap.put("E", Color.rgb(204, 206, 53));
        colorMap.put("Easybuild", Color.rgb(6, 148, 6));
        colorMap.put("eC", Color.rgb(145, 57, 96));
        colorMap.put("Ecere Projects", Color.rgb(145, 57, 96));
        colorMap.put("ECL", Color.rgb(138, 18, 103));
        colorMap.put("ECLiPSe", Color.rgb(0, 29, 157));
        colorMap.put("EditorConfig", Color.rgb(255, 241, 242));
        colorMap.put("Eiffel", Color.rgb(77, 105, 119));
        colorMap.put("EJS", Color.rgb(169, 30, 80));
        colorMap.put("Elixir", Color.rgb(110, 74, 126));
        colorMap.put("Elm", Color.rgb(96, 181, 204));
        colorMap.put("Emacs Lisp", Color.rgb(192, 101, 219));
        colorMap.put("EmberScript", Color.rgb(255, 244, 243));
        colorMap.put("EQ", Color.rgb(167, 134, 73));
        colorMap.put("Erlang", Color.rgb(184, 57, 152));
        colorMap.put("F#", Color.rgb(184, 69, 252));
        colorMap.put("F*", Color.rgb(87, 46, 48));
        colorMap.put("Factor", Color.rgb(99, 103, 70));
        colorMap.put("Fancy", Color.rgb(123, 157, 180));
        colorMap.put("Fantom", Color.rgb(20, 37, 60));
        colorMap.put("Faust", Color.rgb(195, 114, 64));
        colorMap.put("Fennel", Color.rgb(255, 243, 215));
        colorMap.put("FIGlet Font", Color.rgb(255, 221, 187));
        colorMap.put("Filebench WML", Color.rgb(246, 185, 0));
        colorMap.put("fish", Color.rgb(74, 174, 71));
        colorMap.put("Fluent", Color.rgb(255, 204, 51));
        colorMap.put("FLUX", Color.rgb(136, 204, 255));
        colorMap.put("Forth", Color.rgb(52, 23, 8));
        colorMap.put("Fortran", Color.rgb(77, 65, 177));
        colorMap.put("Fortran Free Form", Color.rgb(77, 65, 177));
        colorMap.put("FreeBasic", Color.rgb(134, 125, 177));
        colorMap.put("FreeMarker", Color.rgb(0, 80, 178));
        colorMap.put("Frege", Color.rgb(0, 202, 254));
        colorMap.put("Futhark", Color.rgb(95, 2, 31));
        colorMap.put("G-code", Color.rgb(208, 140, 242));
        colorMap.put("Game Maker Language", Color.rgb(113, 180, 23));
        colorMap.put("GAML", Color.rgb(255, 199, 102));
        colorMap.put("GAMS", Color.rgb(244, 154, 34));
        colorMap.put("GAP", Color.rgb(0, 0, 204));
        colorMap.put("GCC Machine Description", Color.rgb(255, 207, 171));
        colorMap.put("GDScript", Color.rgb(53, 85, 112));
        colorMap.put("GEDCOM", Color.rgb(0, 48, 88));
        colorMap.put("Gemfile.lock", Color.rgb(112, 21, 22));
        colorMap.put("Genie", Color.rgb(251, 133, 93));
        colorMap.put("Genshi", Color.rgb(149, 21, 49));
        colorMap.put("Gentoo Ebuild", Color.rgb(148, 0, 255));
        colorMap.put("Gentoo Eclass", Color.rgb(148, 0, 255));
        colorMap.put("Gerber Image", Color.rgb(210, 11, 0));
        colorMap.put("Gherkin", Color.rgb(91, 32, 99));
        colorMap.put("Git Attributes", Color.rgb(244, 77, 39));
        colorMap.put("Git Config", Color.rgb(244, 77, 39));
        colorMap.put("GLSL", Color.rgb(86, 134, 165));
        colorMap.put("Glyph", Color.rgb(193, 172, 127));
        colorMap.put("Gnuplot", Color.rgb(240, 169, 240));
        colorMap.put("Go", Color.rgb(0, 173, 216));
        colorMap.put("Go Checksums", Color.rgb(0, 173, 216));
        colorMap.put("Go Module", Color.rgb(0, 173, 216));
        colorMap.put("Golo", Color.rgb(136, 86, 42));
        colorMap.put("Gosu", Color.rgb(130, 147, 127));
        colorMap.put("Grace", Color.rgb(97, 95, 139));
        colorMap.put("Gradle", Color.rgb(2, 48, 58));
        colorMap.put("Grammatical Framework", Color.rgb(255, 0, 0));
        colorMap.put("GraphQL", Color.rgb(225, 0, 152));
        colorMap.put("Graphviz (DOT)", Color.rgb(37, 150, 190));
        colorMap.put("Groovy", Color.rgb(66, 152, 184));
        colorMap.put("Groovy Server Pages", Color.rgb(66, 152, 184));
        colorMap.put("Hack", Color.rgb(135, 135, 135));
        colorMap.put("Haml", Color.rgb(236, 226, 169));
        colorMap.put("Handlebars", Color.rgb(247, 147, 30));
        colorMap.put("HAProxy", Color.rgb(16, 109, 169));
        colorMap.put("Harbour", Color.rgb(14, 96, 227));
        colorMap.put("Haskell", Color.rgb(94, 80, 134));
        colorMap.put("Haxe", Color.rgb(223, 121, 0));
        colorMap.put("HiveQL", Color.rgb(220, 226, 0));
        colorMap.put("HLSL", Color.rgb(170, 206, 96));
        colorMap.put("HolyC", Color.rgb(255, 239, 175));
        colorMap.put("HTML", Color.rgb(227, 76, 38));
        colorMap.put("HTML+ECR", Color.rgb(46, 16, 82));
        colorMap.put("HTML+EEX", Color.rgb(110, 74, 126));
        colorMap.put("HTML+ERB", Color.rgb(112, 21, 22));
        colorMap.put("HTML+PHP", Color.rgb(79, 93, 149));
        colorMap.put("HTML+Razor", Color.rgb(81, 43, 228));
        colorMap.put("HTTP", Color.rgb(0, 92, 156));
        colorMap.put("HXML", Color.rgb(246, 135, 18));
        colorMap.put("Hy", Color.rgb(119, 144, 178));
        colorMap.put("IDL", Color.rgb(163, 82, 47));
        colorMap.put("Idris", Color.rgb(179, 0, 0));
        colorMap.put("Ignore List", Color.rgb(0, 0, 0));
        colorMap.put("IGOR Pro", Color.rgb(0, 0, 204));
        colorMap.put("ImageJ Macro", Color.rgb(153, 170, 255));
        colorMap.put("INI", Color.rgb(209, 219, 224));
        colorMap.put("Inno Setup", Color.rgb(38, 75, 153));
        colorMap.put("Io", Color.rgb(169, 24, 141));
        colorMap.put("Ioke", Color.rgb(7, 129, 147));
        colorMap.put("Isabelle", Color.rgb(254, 254, 0));
        colorMap.put("Isabelle ROOT", Color.rgb(254, 254, 0));
        colorMap.put("J", Color.rgb(158, 237, 255));
        colorMap.put("JAR Manifest", Color.rgb(176, 114, 25));
        colorMap.put("Jasmin", Color.rgb(208, 54, 0));
        colorMap.put("Java", Color.rgb(176, 114, 25));
        colorMap.put("Java Properties", Color.rgb(42, 98, 119));
        colorMap.put("Java Server Pages", Color.rgb(42, 98, 119));
        colorMap.put("JavaScript", Color.rgb(241, 224, 90));
        colorMap.put("JavaScript+ERB", Color.rgb(241, 224, 90));
        colorMap.put("Jest Snapshot", Color.rgb(21, 194, 19));
        colorMap.put("JFlex", Color.rgb(219, 202, 0));
        colorMap.put("Jinja", Color.rgb(165, 42, 34));
        colorMap.put("Jison", Color.rgb(86, 179, 203));
        colorMap.put("Jison Lex", Color.rgb(86, 179, 203));
        colorMap.put("Jolie", Color.rgb(132, 49, 121));
        colorMap.put("jq", Color.rgb(199, 37, 78));
        colorMap.put("JSON", Color.rgb(41, 41, 41));
        colorMap.put("JSON with Comments", Color.rgb(41, 41, 41));
        colorMap.put("JSON5", Color.rgb(38, 124, 185));
        colorMap.put("JSONiq", Color.rgb(64, 212, 126));
        colorMap.put("JSONLD", Color.rgb(12, 71, 156));
        colorMap.put("Jsonnet", Color.rgb(0, 100, 189));
        colorMap.put("Julia", Color.rgb(162, 112, 186));
        colorMap.put("Jupyter Notebook", Color.rgb(218, 91, 11));
        colorMap.put("Kaitai Struct", Color.rgb(119, 59, 55));
        colorMap.put("KakouneScript", Color.rgb(111, 128, 66));
        colorMap.put("KiCad Layout", Color.rgb(47, 74, 171));
        colorMap.put("KiCad Legacy Layout", Color.rgb(47, 74, 171));
        colorMap.put("KiCad Schematic", Color.rgb(47, 74, 171));
        colorMap.put("Kotlin", Color.rgb(169, 123, 255));
        colorMap.put("KRL", Color.rgb(40, 67, 10));
        colorMap.put("LabVIEW", Color.rgb(254, 222, 6));
        colorMap.put("Lark", Color.rgb(41, 128, 185));
        colorMap.put("Lasso", Color.rgb(153, 153, 153));
        colorMap.put("Latte", Color.rgb(242, 165, 66));
        colorMap.put("Less", Color.rgb(29, 54, 93));
        colorMap.put("Lex", Color.rgb(219, 202, 0));
        colorMap.put("LFE", Color.rgb(76, 48, 35));
        colorMap.put("LilyPond", Color.rgb(156, 204, 124));
        colorMap.put("Liquid", Color.rgb(103, 184, 222));
        colorMap.put("Literate Agda", Color.rgb(49, 86, 101));
        colorMap.put("Literate CoffeeScript", Color.rgb(36, 71, 118));
        colorMap.put("Literate Haskell", Color.rgb(94, 80, 134));
        colorMap.put("LiveScript", Color.rgb(73, 152, 134));
        colorMap.put("LLVM", Color.rgb(24, 86, 25));
        colorMap.put("Logtalk", Color.rgb(41, 91, 154));
        colorMap.put("LOLCODE", Color.rgb(204, 153, 0));
        colorMap.put("LookML", Color.rgb(101, 43, 129));
        colorMap.put("LSL", Color.rgb(61, 153, 112));
        colorMap.put("Lua", Color.rgb(0, 0, 128));
        colorMap.put("Macaulay2", Color.rgb(216, 255, 255));
        colorMap.put("Makefile", Color.rgb(66, 120, 25));
        colorMap.put("Mako", Color.rgb(126, 133, 141));
        colorMap.put("Markdown", Color.rgb(8, 63, 161));
        colorMap.put("Marko", Color.rgb(66, 191, 242));
        colorMap.put("Mask", Color.rgb(249, 119, 50));
        colorMap.put("Mathematica", Color.rgb(221, 17, 0));
        colorMap.put("MATLAB", Color.rgb(225, 103, 55));
        colorMap.put("Max", Color.rgb(196, 167, 156));
        colorMap.put("MAXScript", Color.rgb(0, 166, 166));
        colorMap.put("mcfunction", Color.rgb(226, 40, 55));
        colorMap.put("Mercury", Color.rgb(255, 43, 43));
        colorMap.put("Meson", Color.rgb(0, 120, 0));
        colorMap.put("Metal", Color.rgb(143, 20, 233));
        colorMap.put("Mirah", Color.rgb(199, 169, 56));
        colorMap.put("mIRC Script", Color.rgb(61, 87, 195));
        colorMap.put("MLIR", Color.rgb(94, 200, 219));
        colorMap.put("Modelica", Color.rgb(222, 29, 49));
        colorMap.put("Modula-2", Color.rgb(16, 37, 63));
        colorMap.put("Modula-3", Color.rgb(34, 51, 136));
        colorMap.put("MoonScript", Color.rgb(255, 69, 133));
        colorMap.put("Motorola 68K Assembly", Color.rgb(0, 93, 170));
        colorMap.put("MQL4", Color.rgb(98, 168, 214));
        colorMap.put("MQL5", Color.rgb(74, 118, 184));
        colorMap.put("MTML", Color.rgb(183, 225, 244));
        colorMap.put("mupad", Color.rgb(36, 73, 99));
        colorMap.put("Mustache", Color.rgb(114, 75, 59));
        colorMap.put("nanorc", Color.rgb(45, 0, 77));
        colorMap.put("NCL", Color.rgb(40, 67, 31));
        colorMap.put("Nearley", Color.rgb(153, 0, 0));
        colorMap.put("Nemerle", Color.rgb(61, 60, 110));
        colorMap.put("nesC", Color.rgb(148, 176, 199));
        colorMap.put("NetLinx", Color.rgb(10, 160, 255));
        colorMap.put("NetLinx+ERB", Color.rgb(116, 127, 170));
        colorMap.put("NetLogo", Color.rgb(255, 99, 117));
        colorMap.put("NewLisp", Color.rgb(135, 174, 215));
        colorMap.put("Nextflow", Color.rgb(58, 196, 134));
        colorMap.put("Nginx", Color.rgb(0, 150, 57));
        colorMap.put("Nim", Color.rgb(255, 194, 0));
        colorMap.put("Nit", Color.rgb(0, 153, 23));
        colorMap.put("Nix", Color.rgb(126, 126, 255));
        colorMap.put("NPM Config", Color.rgb(203, 56, 55));
        colorMap.put("Nu", Color.rgb(201, 223, 64));
        colorMap.put("NumPy", Color.rgb(156, 138, 249));
        colorMap.put("Nunjucks", Color.rgb(61, 129, 55));
        colorMap.put("NWScript", Color.rgb(17, 21, 34));
        colorMap.put("Objective-C", Color.rgb(67, 142, 255));
        colorMap.put("Objective-C++", Color.rgb(104, 102, 251));
        colorMap.put("Objective-J", Color.rgb(255, 12, 90));
        colorMap.put("ObjectScript", Color.rgb(66, 72, 147));
        colorMap.put("OCaml", Color.rgb(59, 225, 51));
        colorMap.put("Odin", Color.rgb(96, 175, 254));
        colorMap.put("Omgrofl", Color.rgb(202, 187, 255));
        colorMap.put("ooc", Color.rgb(176, 183, 126));
        colorMap.put("Opal", Color.rgb(247, 237, 224));
        colorMap.put("Open Policy Agent", Color.rgb(125, 145, 153));
        colorMap.put("OpenCL", Color.rgb(237, 46, 45));
        colorMap.put("OpenEdge ABL", Color.rgb(92, 230, 0));
        colorMap.put("OpenQASM", Color.rgb(170, 112, 255));
        colorMap.put("OpenSCAD", Color.rgb(229, 205, 69));
        colorMap.put("Org", Color.rgb(119, 170, 153));
        colorMap.put("Oxygene", Color.rgb(205, 208, 227));
        colorMap.put("Oz", Color.rgb(250, 183, 56));
        colorMap.put("P4", Color.rgb(112, 85, 181));
        colorMap.put("Pan", Color.rgb(204, 0, 0));
        colorMap.put("Papyrus", Color.rgb(102, 0, 204));
        colorMap.put("Parrot", Color.rgb(243, 202, 10));
        colorMap.put("Pascal", Color.rgb(227, 241, 113));
        colorMap.put("Pawn", Color.rgb(219, 178, 132));
        colorMap.put("PEG.js", Color.rgb(35, 77, 107));
        colorMap.put("Pep8", Color.rgb(199, 111, 91));
        colorMap.put("Perl", Color.rgb(2, 152, 195));
        colorMap.put("PHP", Color.rgb(79, 93, 149));
        colorMap.put("PicoLisp", Color.rgb(96, 103, 175));
        colorMap.put("PigLatin", Color.rgb(252, 215, 222));
        colorMap.put("Pike", Color.rgb(0, 83, 144));
        colorMap.put("PLpgSQL", Color.rgb(51, 103, 144));
        colorMap.put("PLSQL", Color.rgb(218, 216, 216));
        colorMap.put("PogoScript", Color.rgb(216, 0, 116));
        colorMap.put("PostCSS", Color.rgb(220, 58, 12));
        colorMap.put("PostScript", Color.rgb(218, 41, 28));
        colorMap.put("POV-Ray SDL", Color.rgb(107, 172, 101));
        colorMap.put("PowerBuilder", Color.rgb(143, 15, 141));
        colorMap.put("PowerShell", Color.rgb(1, 36, 86));
        colorMap.put("Prisma", Color.rgb(12, 52, 75));
        colorMap.put("Processing", Color.rgb(0, 150, 216));
        colorMap.put("Prolog", Color.rgb(116, 40, 60));
        colorMap.put("Propeller Spin", Color.rgb(127, 162, 167));
        colorMap.put("Pug", Color.rgb(168, 100, 84));
        colorMap.put("Puppet", Color.rgb(48, 43, 109));
        colorMap.put("PureBasic", Color.rgb(90, 105, 134));
        colorMap.put("PureScript", Color.rgb(29, 34, 45));
        colorMap.put("Python", Color.rgb(53, 114, 165));
        colorMap.put("Python console", Color.rgb(53, 114, 165));
        colorMap.put("Python traceback", Color.rgb(53, 114, 165));
        colorMap.put("q", Color.rgb(0, 64, 205));
        colorMap.put("Q#", Color.rgb(254, 214, 89));
        colorMap.put("QML", Color.rgb(68, 165, 28));
        colorMap.put("Qt Script", Color.rgb(0, 184, 65));
        colorMap.put("Quake", Color.rgb(136, 34, 51));
        colorMap.put("R", Color.rgb(25, 140, 231));
        colorMap.put("Racket", Color.rgb(60, 92, 170));
        colorMap.put("Ragel", Color.rgb(157, 82, 0));
        colorMap.put("Raku", Color.rgb(0, 0, 251));
        colorMap.put("RAML", Color.rgb(119, 217, 251));
        colorMap.put("Rascal", Color.rgb(255, 250, 160));
        colorMap.put("RDoc", Color.rgb(112, 21, 22));
        colorMap.put("Reason", Color.rgb(255, 88, 71));
        colorMap.put("Rebol", Color.rgb(53, 138, 91));
        colorMap.put("Record Jar", Color.rgb(6, 115, 186));
        colorMap.put("Red", Color.rgb(245, 0, 0));
        colorMap.put("Regular Expression", Color.rgb(0, 154, 0));
        colorMap.put("Ren'Py", Color.rgb(255, 127, 127));
        colorMap.put("ReScript", Color.rgb(237, 80, 81));
        colorMap.put("reStructuredText", Color.rgb(20, 20, 20));
        colorMap.put("REXX", Color.rgb(217, 14, 9));
        colorMap.put("Ring", Color.rgb(45, 84, 203));
        colorMap.put("Riot", Color.rgb(167, 30, 73));
        colorMap.put("RMarkdown", Color.rgb(25, 140, 231));
        colorMap.put("RobotFramework", Color.rgb(0, 192, 181));
        colorMap.put("Roff", Color.rgb(236, 222, 190));
        colorMap.put("Roff Manpage", Color.rgb(236, 222, 190));
        colorMap.put("Rouge", Color.rgb(204, 0, 136));
        colorMap.put("Ruby", Color.rgb(112, 21, 22));
        colorMap.put("RUNOFF", Color.rgb(102, 90, 78));
        colorMap.put("Rust", Color.rgb(222, 165, 132));
        colorMap.put("SaltStack", Color.rgb(100, 100, 100));
        colorMap.put("SAS", Color.rgb(179, 73, 54));
        colorMap.put("Sass", Color.rgb(165, 59, 112));
        colorMap.put("Scala", Color.rgb(194, 45, 64));
        colorMap.put("Scaml", Color.rgb(189, 24, 26));
        colorMap.put("Scheme", Color.rgb(30, 74, 236));
        colorMap.put("Scilab", Color.rgb(202, 15, 33));
        colorMap.put("SCSS", Color.rgb(198, 83, 140));
        colorMap.put("sed", Color.rgb(100, 185, 112));
        colorMap.put("Self", Color.rgb(5, 121, 170));
        colorMap.put("ShaderLab", Color.rgb(34, 44, 55));
        colorMap.put("Shell", Color.rgb(137, 224, 81));
        colorMap.put("Shen", Color.rgb(18, 15, 20));
        colorMap.put("Singularity", Color.rgb(100, 230, 173));
        colorMap.put("Slash", Color.rgb(0, 126, 255));
        colorMap.put("Slice", Color.rgb(0, 63, 162));
        colorMap.put("Slim", Color.rgb(43, 43, 43));
        colorMap.put("Smalltalk", Color.rgb(89, 103, 6));
        colorMap.put("Smarty", Color.rgb(240, 192, 64));
        colorMap.put("SmPL", Color.rgb(201, 73, 73));
        colorMap.put("Solidity", Color.rgb(170, 103, 70));
        colorMap.put("SourcePawn", Color.rgb(246, 158, 29));
        colorMap.put("SPARQL", Color.rgb(12, 69, 151));
        colorMap.put("SQF", Color.rgb(63, 63, 63));
        colorMap.put("SQL", Color.rgb(227, 140, 0));
        colorMap.put("SQLPL", Color.rgb(227, 140, 0));
        colorMap.put("Squirrel", Color.rgb(128, 0, 0));
        colorMap.put("SRecode Template", Color.rgb(52, 138, 52));
        colorMap.put("Stan", Color.rgb(178, 1, 29));
        colorMap.put("Standard ML", Color.rgb(220, 86, 109));
        colorMap.put("Starlark", Color.rgb(118, 210, 117));
        colorMap.put("Stata", Color.rgb(26, 95, 145));
        colorMap.put("StringTemplate", Color.rgb(63, 179, 79));
        colorMap.put("Stylus", Color.rgb(255, 99, 71));
        colorMap.put("SubRip Text", Color.rgb(158, 1, 1));
        colorMap.put("SugarSS", Color.rgb(47, 204, 159));
        colorMap.put("SuperCollider", Color.rgb(70, 57, 11));
        colorMap.put("Svelte", Color.rgb(255, 62, 0));
        colorMap.put("SVG", Color.rgb(255, 153, 0));
        colorMap.put("Swift", Color.rgb(240, 81, 56));
        colorMap.put("SystemVerilog", Color.rgb(218, 225, 194));
        colorMap.put("Tcl", Color.rgb(228, 204, 152));
        colorMap.put("Terra", Color.rgb(0, 0, 76));
        colorMap.put("TeX", Color.rgb(61, 97, 23));
        colorMap.put("Textile", Color.rgb(255, 231, 172));
        colorMap.put("TextMate Properties", Color.rgb(223, 102, 228));
        colorMap.put("Thrift", Color.rgb(209, 33, 39));
        colorMap.put("TI Program", Color.rgb(160, 170, 135));
        colorMap.put("TLA", Color.rgb(75, 0, 121));
        colorMap.put("TOML", Color.rgb(156, 66, 33));
        colorMap.put("TSQL", Color.rgb(227, 140, 0));
        colorMap.put("TSV", Color.rgb(35, 115, 70));
        colorMap.put("TSX", Color.rgb(43, 116, 137));
        colorMap.put("Turing", Color.rgb(207, 20, 43));
        colorMap.put("Twig", Color.rgb(193, 208, 38));
        colorMap.put("TXL", Color.rgb(1, 120, 184));
        colorMap.put("TypeScript", Color.rgb(43, 116, 137));
        colorMap.put("Unified Parallel C", Color.rgb(78, 54, 23));
        colorMap.put("Unity3D Asset", Color.rgb(34, 44, 55));
        colorMap.put("Uno", Color.rgb(153, 51, 204));
        colorMap.put("UnrealScript", Color.rgb(165, 76, 77));
        colorMap.put("UrWeb", Color.rgb(204, 204, 238));
        colorMap.put("V", Color.rgb(79, 135, 196));
        colorMap.put("Vala", Color.rgb(251, 229, 205));
        colorMap.put("Valve Data Format", Color.rgb(242, 96, 37));
        colorMap.put("VBA", Color.rgb(134, 125, 177));
        colorMap.put("VBScript", Color.rgb(21, 220, 220));
        colorMap.put("VCL", Color.rgb(20, 138, 168));
        colorMap.put("Verilog", Color.rgb(178, 183, 248));
        colorMap.put("VHDL", Color.rgb(173, 178, 203));
        colorMap.put("Vim Help File", Color.rgb(25, 159, 75));
        colorMap.put("Vim Script", Color.rgb(25, 159, 75));
        colorMap.put("Vim Snippet", Color.rgb(25, 159, 75));
        colorMap.put("Visual Basic .NET", Color.rgb(148, 93, 183));
        colorMap.put("Volt", Color.rgb(31, 31, 31));
        colorMap.put("Vue", Color.rgb(65, 184, 131));
        colorMap.put("wdl", Color.rgb(66, 241, 244));
        colorMap.put("Web Ontology Language", Color.rgb(91, 112, 189));
        colorMap.put("WebAssembly", Color.rgb(4, 19, 59));
        colorMap.put("Wikitext", Color.rgb(252, 87, 87));
        colorMap.put("Windows Registry Entries", Color.rgb(82, 213, 255));
        colorMap.put("wisp", Color.rgb(117, 130, 209));
        colorMap.put("Wollok", Color.rgb(162, 55, 56));
        colorMap.put("World of Warcraft Addon Data", Color.rgb(247, 228, 63));
        colorMap.put("X10", Color.rgb(75, 107, 239));
        colorMap.put("xBase", Color.rgb(64, 58, 64));
        colorMap.put("XC", Color.rgb(153, 218, 7));
        colorMap.put("XML", Color.rgb(0, 96, 172));
        colorMap.put("XML Property List", Color.rgb(0, 96, 172));
        colorMap.put("Xojo", Color.rgb(129, 189, 65));
        colorMap.put("Xonsh", Color.rgb(40, 94, 239));
        colorMap.put("XQuery", Color.rgb(82, 50, 231));
        colorMap.put("XSLT", Color.rgb(235, 140, 235));
        colorMap.put("Xtend", Color.rgb(36, 37, 93));
        colorMap.put("Yacc", Color.rgb(75, 108, 75));
        colorMap.put("YAML", Color.rgb(203, 23, 30));
        colorMap.put("YARA", Color.rgb(34, 0, 0));
        colorMap.put("YASnippet", Color.rgb(50, 171, 144));
        colorMap.put("ZAP", Color.rgb(13, 102, 94));
        colorMap.put("ZenScript", Color.rgb(0, 188, 209));
        colorMap.put("Zephir", Color.rgb(17, 143, 158));
        colorMap.put("Zig", Color.rgb(236, 145, 92));
        colorMap.put("ZIL", Color.rgb(220, 117, 229));
        colorMap.put("Zimpl", Color.rgb(214, 119, 17));
    }

    public static Color getBackColor(String language) {
        return colorMap.getOrDefault(language, Color.rgb(62, 123, 145));
    }

    /**
     * Returns if Dark using YIQ
     *
     * @param color color to check
     * @return true, if color is too dark
     */
    private static boolean isDark(Color color) {
        double res = 255 * (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
        return res < 128;
    }

    /**
     * Gets the color of the font.
     *
     * @param language of the font.
     * @return Color of the font.
     */
    public static Color getFontColor(String language) {
        Color back = getBackColor(language);
        if (isDark(back)) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    /**
     * Returns the hex of the color.
     *
     * @param color
     * @return String representation of the hex of the color.
     */
    public static String getHex(Color color) {
        return color.toString().replace("0x", "#");
    }
}
