{-# OPTIONS -Wall #-}

--------------------------------------------------------------------------------  

module AATree (
  AATree,        -- type of AA search trees
  emptyTree,     -- AATree a
  split,         -- Ord a => AATree -> AATree
  skew,          -- AATree a -> AATree a
  ex2,
  ex3,
  get,           -- Ord a => a -> AATree a -> Maybe a
  insert,        -- Ord a => a -> AATree a -> AATree a
  inorder,       -- AATree a -> [a]
  remove,        -- Ord a => a -> AATree a -> AATree a
  size,          -- AATree a -> Int
  height,        -- AATree a -> Int
  checkTree      -- Ord a => AATree a -> Bool
 ) where

--------------------------------------------------------------------------------
--import Test.QuickCheck

ex2 :: AATree Int
ex2 = Node 1 1 Null (Node 7 2 Null (Node 6 3 Null Null))
ex3 :: AATree Int
ex3 = Node 1 1 Null (Node 7 2 Null (Node 6 3 (Node 4 4 Null Null) Null))



type Level = Int

-- AA search trees
data AATree a = Null | Node a Level (AATree a) (AATree a)
  deriving (Eq, Show, Read)

-- Returns the emty tree
emptyTree :: AATree a
emptyTree = Null

-- Return the 
get :: Ord a => a -> AATree a -> Maybe a
get _ Null = Nothing
get x (Node v _ l r)
            | x < v     = get x l
            | x > v     = get x r
            | otherwise = Just x


insert :: Ord a => a -> AATree a -> AATree a
insert x Null = Node x 1 Null Null
insert x aaTree@(Node v lvl l r)
            | x  < v    = split $ skew $ Node v lvl (insert x l) r
            | x  > v    = split $ Node v lvl l (insert x r)
            | otherwise = aaTree


split :: Ord a => AATree a -> AATree a
split aaTree@(Node v lvl l (Node v' lvl' l' aaTree''@(Node _ lvl'' _ _))) =
  if comp then Node v' (lvl'+1) (Node v lvl l l') aaTree''
    else aaTree
      where comp = lvl == lvl' && lvl' == lvl''
split aaTree = aaTree


skew :: Ord a => AATree a -> AATree a
skew aaTree@(Node x lvl (Node x' lvl' l r) r1) = 
  if lvl == lvl' 
    then Node x' lvl' l (Node x lvl r r1)
    else aaTree
skew aaTree = aaTree

inorder :: AATree a -> [a]
inorder Null = []
inorder (Node v _ l r) = inorder l ++ v : inorder r

size :: AATree a -> Int
size Null           = 0
size (Node _ _ l r) = size l + size r + 1

height :: AATree a -> Int
height Null = 0
height (Node _ _ l r) = 1 + max (height l) (height r)

level :: AATree a -> Level
level Null = 0
level (Node _ lvl _ _ ) = lvl

value :: AATree a -> a
value Null = error "unused pattermatch"
value (Node val _ _ _) = val
--------------------------------------------------------------------------------
-- Optional function

remove :: Ord a => a -> AATree a -> AATree a
remove = error "   "

--------------------------------------------------------------------------------
-- Check that an AA tree is ordered and obeys the AA invariants
checkTree :: Ord a => AATree a -> Bool
checkTree root =
  isSorted (inorder root) &&
  all checkLevels (nodes root)
  where
    nodes x
      | isEmpty x = []
      | otherwise = x:nodes (leftSub x) ++ nodes (rightSub x)

-- True if the given list is ordered
isSorted :: Ord a => [a] -> Bool
isSorted [] = True
isSorted [_] = True
isSorted (x:y:xs) = (x <= y) && isSorted (y:xs)

-- Check if the invariant is true for a single AA node
-- You may want to write this as a conjunction e.g.
--   checkLevels node =
--     leftChildOK node &&
--     rightChildOK node &&
--     rightGrandchildOK node
-- where each conjunct checks one aspect of the invariant

checkLevels :: Ord a => AATree a -> Bool
checkLevels (Node _ _ Null Null) = True
checkLevels aaTree               = check_left aaTree && check_right aaTree

check :: Ord a => a -> Level -> AATree a -> (a -> Bool) -> Bool
check _ 1   Null   _    = True
check _ lvl branch func = (lvl == level branch) || (lvl == level branch + 1 && func (value branch))


check_left, check_right :: Ord a => AATree a -> Bool
check_left Null = True
check_left (Node v lvl l _)  = check v lvl l (< v)
check_right Null = True
check_right (Node v lvl _ r) = check v lvl r (> v)

--rightGrandchildOK :: AATree a -> Bool
isEmpty :: AATree a -> Bool
isEmpty Null = True
isEmpty _    = False

leftSub :: AATree a -> AATree a
leftSub Null           = emptyTree
leftSub (Node _ _ l _) = l 

rightSub :: AATree a -> AATree a
rightSub Null           = emptyTree
rightSub (Node _ _ _ r) = r 

{-insert_list :: Ord a => [a] -> AATree a -> AATree a
insert_list [] tree     = tree
insert_list (x:xs) tree = insert_list xs (insert x tree)

from_list :: Ord a => [a] -> AATree a
from_list xs = insert_list xs emptyTree

--------- Propertys to prove with quickCheck -----------
-- The level of every leaf node is one.
prop_invariant1 :: AATree a -> Bool
prop_invariant1 (Node _ lvl Null Null) = lvl == 1
prop_invariant1 (Node _ _ l r) = prop_invariant1 l && prop_invariant1 r 
prop_invariant1 Null = True

-- The level of every left child is exactly one less than that of its parent.
prop_invariant2 :: AATree a -> Bool
prop_invariant2 (Node _ lvl lTree@(Node _ lvl' _ _) r) = 
      lvl == 1 + lvl' && prop_invariant2 lTree && prop_invariant2 r
prop_invariant2 (Node _ _ l r) = prop_invariant2 l && prop_invariant2 r
prop_invariant2 Null = True

-- The level of every right child is equal to or one less than that of its parent.
prop_invariant3 :: AATree a -> Bool
prop_invariant3 (Node _ lvl l rT@ (Node _ lvl' _ _)) = 
      lvl - lvl' <= 1 && prop_invariant3 l && prop_invariant3 rT
prop_invariant3 (Node _ _ l r) = prop_invariant3 l && prop_invariant3 r
prop_invariant3 Null = True

-- The level of every right grandchild is strictly less than that of its grandparent.
-- Every node of level greater than one has two children.

---------- QuickCheck Generator ----------
instance (Arbitrary a, Ord a) => Arbitrary (AATree a) where
  arbitrary = rTree

rTree :: (Arbitrary a, Ord a) => Gen (AATree a)
rTree = fmap from_list (listOf arbitrary)

-}
