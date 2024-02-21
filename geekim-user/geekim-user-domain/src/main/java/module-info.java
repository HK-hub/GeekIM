/**
 * @ClassName : module-info
 * @author : HK意境
 * @date : 2024/2/20 17:12
 * @description :在领域驱动设计（DDD）中，领域层（Domain Layer）是业务逻辑的核心部分，它包含了领域模型、业务规则和实体行为。根据DDD的建议，领域层的内容可以按照以下结构进行组织和分包：
 * 实体（Entities）：
 * 这个包包含所有代表业务核心概念的实体类。例如，在用户服务中可能有一个User实体，包含用户的属性和业务规则。
 * 值对象（Value Objects）：
 * 包含表示不变性或描述性的值对象，如EmailAddress、PhoneNumber等，这些值对象本身也具有一定的业务约束。
 * 聚合（Aggregates）：
 * 聚合是领域模型中的一个高级概念，通常由一个聚合根（Aggregate Root）以及相关的实体和值对象组成。例如，UserProfile可能是一个聚合根，其中包含多个相关实体和值对象。
 * 领域服务（Domain Services）：
 * 当某个业务操作跨越了单个实体或聚合时，可以通过领域服务来实现跨领域的复杂业务逻辑。例如，UserService可能处理与用户注册、登录、权限变更等相关的业务流程。
 * 领域事件（Domain Events）：
 * 用于捕获领域内的关键业务动作，当发生特定业务事件时触发并发布，供其他组件订阅和响应。例如，UserRegisteredEvent、PasswordChangedEvent等。
 * 领域仓库（Repositories）：
 * 领域仓库接口定义了如何持久化和检索领域对象的方法。具体的实现会放在基础设施层（Infrastructure Layer），而领域层只关注其抽象接口。
 * 领域对象工厂（Factories）：
 * 用于创建领域对象实例，特别是在涉及复杂构造过程的情况下。
 * 具体到代码分包结构，可以根据上述内容进行如下组织（以Java为例）：
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
module geekim.user.domain {



}