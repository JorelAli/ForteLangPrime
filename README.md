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

Java is compatible with all operating systems. You write code once and can run it anywhere. So far, I've not found any programming language that also has this feature (except Java). Since I have a very strong understanding of Java, my main aims are to create some well-optimised bytecode such that it's fully compatible with Java (as opposed to say, Kotlin or Groovy, which requires additional libraries (e.g. Kotlin Runtime)).

### Core concepts/features

- [ ] Compiles down to Java Bytecode
- [ ] Few keywords, providing more flexibility to the user for what to name variables/functions
- [ ] Statically typed
- [ ] Lazily evaluated
- [ ] Set data structure (like Nix)
- [ ] Doesn't require indentation to declare scope (like Java)
- [ ] Arbitrary precision numbers (like Python)

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
- If statements

-----

## Syntax (preview)

### Record updating

ForteLangPrime's record updating will primarily be inspired using ForteLang's set operators. ForteLang provides the following extra operators that perform set operations:

```python
x /+ y ## Set union
x /- y ## Set intersection
x // y ## Set difference
```

Hence, updating a record is simply performing a union of a set with a set of certain elements, and replacing any common attributes. Since "sets" in ForteLang are more like "key-value pairs", the set operators will replace similar keys on the left set with the ones on the right. For example:
```python
{ x = 3; } /+ { x = 4; } ## { x = 4; }
```

### Pattern matching

Pattern matching is performed using the `?=` operator. `?= expression | case => result | ... | => result`

For example, to check if a list is empty, we pattern match on the variable `list` (which is of type "List of `a`s"). If the list is `[]` (an empty list), then we return `true`. Otherwise, we return `false`.
```
isEmpty list<[a]> -> <Bool> =
  ?= list | [] => true | => false
```
