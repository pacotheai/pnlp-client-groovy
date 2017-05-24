import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.ast.ClassNode

static Closure<Boolean> classPackageContains(String pkg) {
  return { SourceUnit unit ->
    unit.AST.classes.any { ClassNode classNode ->
      classNode.packageName.contains(pkg)
    }
  } as Closure<Boolean>
}

static Closure<Boolean> classNameNotEndsWith(String className) {
  return { SourceUnit unit ->
    !unit.AST.classes.any { ClassNode classNode ->
      classNode.name.endsWith(className)
    }
  } as Closure<Boolean>
}

/**
 * This compiler configuration
 *
 * @since 0.1.0
 */
withConfig(configuration) {

  source(unitValidator: classPackageContains('domain')) {
    ast(groovy.transform.ToString)
    ast(groovy.transform.TupleConstructor)
    ast(groovy.transform.EqualsAndHashCode)
  }

  source(unitValidator: classNameNotEndsWith('Spec')) {
    ast(groovy.transform.CompileStatic)
  }
}
