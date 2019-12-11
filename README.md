# ForteLangPrime
A functionally pure typed programming language, that runs on the JVM. The successor to [ForteLang](https://github.com/JorelAli/ForteLang).

## Elymology

Helper functions in functional programming languages are normally denoted with the `'` (pronounced "prime") symbol. For example, if you have a function `factorial`, a helper function that is used by the factorial function would be named `factorial'`. ForteLangPrime is the successor to ForteLang, hence I decided to call it ForteLangPrime (Since `ForteLang'` isn't very name friendly on websites/files/directories).

## Design choices

ForteLangPrime builds on a number of personal projects and university-based coursework assignments. In particular:

- Compiler Design coursework, which involved creating a compiler in C++.
- My third year project, which involves creating various programming languages and interpreters.
- ForteLang, my first attempt at creating a functional programming language in Java.

In addition to these projects, inspiration was also taken from:

- [Nix Expression Language](https://nixos.wiki/wiki/Nix_Expression_Language), a purely functional language used by the Nix package manager
- [Elm](https://elm-lang.org), a purely functional language primarily used in web-development
- [Haskell](https://www.haskell.org/), an advanced, purely functional programming language

### Why Java?

Java is compatible with all operating systems. You write code once and can run it anywhere. So far, I've not found any programming language that also has this feature (I'm dismissing things that also run on the JVM, like Scala) that works as reliably as Java does. Since I have a very strong understanding of Java, my main aims are to create some well-optimised bytecode such that it's fully compatible with Java (as opposed to say, Kotlin or Groovy, which requires additional libraries (e.g. Kotlin Runtime/Standard Library)).

### Core concepts/features

- [ ] Compiles down to Java Bytecode
- [ ] Few keywords, providing more flexibility to the user for what to name variables/functions
- [ ] Statically typed
- [ ] Lazily evaluated
- [ ] Set data structure (like Nix)
- [ ] Doesn't require indentation to declare scope (like Java)
- [ ] Arbitrary precision numbers (like Python)
- [ ] Pragmas (like C)

### Other sneaky features

- Ability to nest comment blocks within comment blocks (like Haskell)
- Pattern matching (like Haskell)
- Type aliases (like Elm)
- Function pipes AKA the `|>` operator (like Elm)
- Record updating (like Elm). For example:
  ```python
  point = { x = 2; y = 3; }    ## {x=2, y=3}
  newPoint = {point | x = 3; } ## {x=3, y=3}
  ```
  
### Features that won't be added

- Custom operators
- Custom types (only type aliases)

-----

# Syntax (preview)

## Sets (records)

ForteLangPrime's main data types are "sets", which are similar to records in Haskell and Elm. Expressions declared in a set must be followed with a `;`. For example, a set that contains some maths helper functions would look like:

```haskell
{
  add i1<Num> -> i2<Num> -> <Num> = i1 + i2;
  mul i1<Num> -> i2<Num> -> <Num> = i1 * i2;
  pow i1<Num> -> i2<Num> -> <Num> = i1 ^ i2;
}
```

```haskell
{
  mathLib <{
     add i1<Num> -> i2<Num> -> <Num>;
     mul i1<Num> -> i2<Num> -> <Num>;
     pow i1<Num> -> i2<Num> -> <Num>;
     advanceMath <{
       sin i1<Num> -> <Num>;
       cos i1<Num> -> <Num>;
       tan i1<Num> -> <Num>;
     }>;
  }> = {
    add i1<Num> -> i2<Num> -> <Num> = i1 + i2;
    mul i1<Num> -> i2<Num> -> <Num> = i1 * i2;
    pow i1<Num> -> i2<Num> -> <Num> = i1 ^ i2;
    advanceMath <{
      sin i1<Num> -> <Num>;
      cos i1<Num> -> <Num>;
      tan i1<Num> -> <Num>;
    }> = {
      sin i1<Num> -> <Num> = #[Sine implementation here]#;
      cos i1<Num> -> <Num> = #[Cosine implementation here]#;
      tan i1<Num> -> <Num> = #[Tangent implementation here]#;
    }
  };
}
```

## Record updating

ForteLangPrime's record updating will primarily be inspired using ForteLang's set operators. ForteLang provides the following extra operators that perform set operations:

```haskell
x /+ y ## Set union
x /- y ## Set intersection
x // y ## Set difference
```

Hence, updating a record is simply performing a union of a set with a set of certain elements, and replacing any common attributes. Since "sets" in ForteLang are more like "key-value pairs", the set operators will replace similar keys on the left set with the ones on the right. For example:
```python
{ x = 3; } /+ { x = 4; } ## { x = 4; }
```

## Pattern matching

Pattern matching is performed using the `?=` operator. `?= expression | case => result | ... | => result`

For example, to check if a list is empty, we pattern match on the variable `list` (which is of type "List of `a`s"). If the list is `[]` (an empty list), then we return `true`. Otherwise, we return `false`.
```
isEmpty list<[a]> -> <Bool> =
  ?= list 
  | [] => true 
  | => false
```

## Guards (conditional statements)

Guards are used to handle conditional statements, by using the `?:` operator.

```haskell
max num1<Num> -> num2<Num> -> <Num> =
  ?: 
  | num1 > num2 => num1
  | => num2
```

## Type aliases

Type aliases let you refer to sets easily. They have to start with a capital letter.

```haskell
Color = {
  red<Num>;
  green<Num>;
  blue<Num>;
}

Person = {
  name<String>;
  age<Num>;
  eyeColor<Color>;
}
```

## Enums

Enums are basically sets, which use the following syntax:

```haskell
Fruit = {| BANANA, APPLE, PEAR, ORANGE, STRAWBERRY |}
```

They can be referred to using the same syntax as sets, and _have_ to be fully qualified:

```haskell
fruitToString fruit<Fruit> -> <String> =
  ?= fruit
  | fruit.BANANA => "banana"
  | fruit.APPLE => "apple"
  | fruit.PEAR => "pear"
  | fruit.ORANGE => "orange"
  | fruit.STRAWBERRY => "strawberry"
```

As enums are similar to type declarations, they _must_ be named with a capital letter

## Pragmas

At the moment, pragmas are extra settings to pass to the compiler, similar to say Haskell extensions or something.

Currently (as of time of writing), I only plan to have a few hardcoded pragmas in ForteLangPrime. In the end, I'm thinking of creating an API which lets you write pragmas in Java, which can be added to the compiler at compile time.

The following pragmas have been planned:

- `@NUMBER_COMMAS` - lets you declare numbers with commas in them. For example: `1,000,000`
- `@ABORT` - an Easter egg that allows you to declare `a@bort` anywhere in your code for 

-----

# Grammar

```haskell
program -> pragmas expression
pragmas -> 
  | pragma pragmas
  | EPSILON
pragma -> @PRAGMA_IDENT
```

Function application & expressions
```haskell
application ->
  | '(' guards ')' params
  | '(' match ')' params
  | '(' lambda ')' params
  | variable params
params -> 
  | EPSILON
  | expression params

expression ->
  | guards
  | match
  | set
  | list
  | string
  | number
  | lambda
  | variable
  | if
  | '(' expression ')'
  | application
  | application '|>' expression
```

```haskell
lambda -> '(' functionDeclaration ')'
number -> [0-9]+(.[0-9]+)?
string -> '"' STRING '"'  
```

Types
```haskell
types -> 
  | identifier '<' type '>'
  | identifier '<' type '>' '->' types
identifier -> 
  | [a-zA-Z_'@-?!]+
  | EPSILON
type ->
  | 'Num'
  | 'String'
  | 'Bool'
  | '[' type ']'
  | '{' setTypes '}'
setTypes ->
  | otherSetTypes
otherSetTypes ->
  | namedIdentifier '<' type '>'
  | namedIdentifier '<' type '>' ',' otherSetTypes
namedIdentifier -> [a-zA-Z_'@-?!]+
```

Sets
```haskell
functionDeclaration -> functionName types '=' expression
functionName -> [a-zA-Z_'@-?!]+

set -> '{' setDeclarations '}'
setDeclarations ->
  | setElements
  | EPSILON
setElements ->
  | functionDeclaration ';' setElements
  | functionDeclaration ';'
```

Lists
```haskell
list -> '[' listContents ']'
listContents -> 
  | listElements
  | EPSILON
listElements -> 
  | expression ',' listElements
  | expression
```

Matching & Guards
```haskell
match -> '?=' expression matchStatements '|' '=>' expression
matchExpressions -> matchExpression matchExpressions | EPSILON
matchExpression -> '|' expression '=>' expression
  
guards -> '?:' innerGuards '|' '=>' expression
innerGuards -> guard innerGuards | EPSILON
guard -> '|' expression '=>' expression
```

Enums
```haskell
enum -> `{|` enumElements `|}`
```

If statements
```haskell
if -> 'if' expression 'then' expression 'else' expression
```
