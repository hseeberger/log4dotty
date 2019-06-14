# log4dotty #

This is – at least at this moment in time – just an experiment to learn Dotty / Scala 3. This software is neither API stable nor tested, i.e. by no means production ready. 

The idea is to provide a macro based simplified yet efficient logging API which allows to write code like 

``` scala
logger.debug("Hello")
```

which gets compiled to the enabled-checked variant

``` scala
if (logger.isDebugEnabled) logger.debug("Hello")
```

## Contribution policy ##

Contributions via GitHub pull requests are gladly accepted from their original author. Along with
any pull requests, please state that the contribution is your original work and that you license
the work to the project under the project's open source license. Whether or not you state this
explicitly, by submitting any copyrighted material via pull request, email, or other means you
agree to license the material under the project's open source license and warrant that you have the
legal authority to do so.

## License ##

This code is open source software licensed under the
[Apache-2.0](http://www.apache.org/licenses/LICENSE-2.0) license.
