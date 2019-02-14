## Specifying and loading a configuration DSL Kotlin Script

This is just a simple example that

* Creates a small Kotlin DSL
* Uses it to specify a configuration DSL in a type safe way
* Uses arrow library goodies, Try, Either, Monad bindings and comprehensions to manage errors and validations
* Uses a script engine loader to load and compile a KotlinScript file at runtime
* Loads the configuration DSL from a kotlinscript file