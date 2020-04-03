# ForteLang′
A functionally pure statically-typed programming language, that runs on the JVM. The successor to [ForteLang](https://github.com/JorelAli/ForteLang).

## Elymology

Helper functions in functional programming languages are normally denoted with the `′` (pronounced "prime") symbol. For example, if you have a function `factorial`, a helper function that is used by the factorial function would be named `factorial′`. ForteLang′ is the successor to ForteLang, hence I decided to call it ForteLang′. _(However, since the name uses a symbol that nobody can type, it's easier to refer to it as ForteLangPrime)_.

## Inspiration & Design

ForteLang′ builds on a number of personal projects and university-based coursework assignments. In particular:

- Compiler Design coursework, which involved creating a compiler in C++.
- My third year project, which involves creating various programming languages and interpreters.
- ForteLang, my first attempt at creating a functional programming language in Java.

In addition to these projects, inspiration was also taken from:

- [Nix Expression Language](https://nixos.wiki/wiki/Nix_Expression_Language), a purely functional language used by the Nix package manager
- [Elm](https://elm-lang.org), a purely functional language primarily used in web-development
- [Haskell](https://www.haskell.org/), an advanced, purely functional programming language

### Goals

The goals for ForteLang′ is better described in my [blog post](https://www.jorel.dev/blog/Making-A-Better-Programming-Language/#a-new-language-design), but the summary is as follows:

- **A purely functional Java-compatible programming language.** The main aim is to write a 'library' language that is compatible with Java, but also maintaining purity. In order to do so, ForteLang′ only allows functions to be called from other ForteLang′ files and not from regular Java files.
- **Less bloat.** Current implementations of functional languages that run on the JVM require absurd amounts of additional standard libraries and extra code added to classes (such as metadata, getters/setters etc.). ForteLang′ is designed to require an incredibly small (if possible non-existent) standard library and aims not to add any unnecessary bloatiness to the compiled class files
- **Lazy evaluation**
- **A compiled language.** Compiling directly to Java bytecode gives it the best interoperability with Java. Also, I want to apply the stuff I've learnt into actually making a compiled language instead of an interpreted language

### Core concepts/features

- Compiles down to Java Bytecode
- Statically typed
- Lazily evaluated
- Purely functional

### Other sneaky features

ForteLang′ also plans on having the following features:

- Nested comments
- Algebraic data types
- Function pipes AKA the `|>` operator (like Elm)
- Record updating (like Elm). For example:
  ```python
  point = { x = 2; y = 3; }    ## {x=2, y=3}
  newPoint = { point | x = 3; } ## {x=3, y=3}
  ```

-----

# Syntax (currently available)

For a quick example of existing features, refer to the [test.flp](https://github.com/JorelAli/ForteLangPrime/blob/master/test.flp) file

## Library declaration

Libraries are declared as follows:

```haskell
Library LibraryName {
    ## Exports
} {
    ## Declarations
}
```

### Exports

Exports are added using the `export` keyword and are used to export function names. They require a semicolon at the end. For example:

```haskell
export myFunction;
export myOtherFunction;
```

All functions can be exported using the `*` operator. For example:

```haskell
export *;
```

## Function declarations

Function declarations are of the following formats:

```haskell
functionName <types> = <expression>;
```

Function types can be parameterised by an identifier or not. In this example, we represent a function that takes in an `Int` and returns and `Int`:

```haskell
## No parameter name
functionName <Int> -> <Int> = ## Some expression

## With parameter name
functionName a<Int> -> <Int> = ## Some expression
```

Generic function declarations have to declare the name of the generic type:

```haskell
<T> functionName a<T> -> <T> = ## Some expression
```

Multiple generic types are separated with commas:

```haskell
<T, U> functionType a<T> -> b<U> -> <Bool> = ## Some expression
```

## Record type declarations

Record types are declared as follows. Note how you _do not_ need a semicolon at the end:

```haskell
type MyRecord = {
    value<Int>;
    ## Etc.
}
```

## Custom operator declarations

Custom operators are declared as follows:

```haskell
infix 2 opName (<>) <Bool> -> <Bool> -> <Bool> = ## Some expression
```

The order of associativity is set by using `infix` (no associativity), `infixl` (left associativity) or `infixr` (right associativity). The number after that is the precendence (higher is more tightly binding). The operator is declared in brackets after the operator's name (which is used when exporting custom operator functions).

-----

# Syntax (preview)

## Library declaration

Libraries are the 'main type' of a ForteLang′ file. The main aim is to declare a list of functions which can be used by other JVM languages.

```haskell
Library LibraryName {

    import "someFile.flp" as Blah;
    export addOne; 
	export identity;

} {

    addOne i<Num> -> <Num> = i + 1;
	<T> identity input<T> -> <T> = input;

}
```

> ## Script declaration
>
> Scripts are still a concept for ForteLang′, but the general idea is that they can be run from tools such as `jshell`.
>
> ```haskell
> Script {} {
>  main input<String> -> <String> =
> 	  ## Your code here;
> }
> ```
>
> In script declarations, the metadata is used to declare which functions can be used in the scope, for example when imported using a repl.
>
> For example, opening the repl with an imported script, say:
>
> ```haskell
> Script {
>  export hello
> } {
>  hello <String> = "hello";
> 	bye <String> = "bye";
> }
> ```
>
> will import the function `hello` into the scope of the repl, but the function `bye` will not be available for use in the repl.

## Algebraic Data Types & Type Generics

ForteLang′ will include algebraic data types and support for generics.

```haskell
{
    ## Example of a binary tree using records
    type <T> Tree<T> = Node<{
        lhs<Tree<T>>;
        rhs<Tree<T>>;
    }> || Leaf<T>;
    
    ## Example of a binary tree without using records
    type <T> Tree<T> = Node<Tree<T>, Tree<T>> || Leaf<T>;
}
```


## Record types

ForteLang′ will also include record types, which are basically structs in C/C++. Expressions declared in a set must be followed with a `;`. 

For example, say we want to represent RGB colors:

```haskell
type Color = {
    red<Int>;
    green<Int>;
    blue<Int>;
}
```

Records can be constructed:

```haskell
getRed <Color> = {
    red = 255;
    green = 0;
    blue = 0;
};
```

and used as a base for other records:

```haskell
getYellow <Color> = { getRed | green = 255; }
```

### Anonymous record types

Record types _**have**_ to be declared before usage, unlike a language like Elm where you can have "anonymous record types" so to speak. In other words, you **can't** do something like this:

```haskell
getComplexNumber <{ re<Int>; im<Int>; }> = {
    re = 0;
    im = 1;
}
```

## Pattern matching

Pattern matching is performed using the `switch` operator. `switch expression | case => result | ... | => result`

For example, to check if a list is empty, we pattern match on the variable `list` (which is of type "List of `a`s"). If the list is `[]` (an empty list), then we return `true`. Otherwise, we return `false`.

```
<a> isEmpty list<[<a>]> -> <Bool> =
  switch list 
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
