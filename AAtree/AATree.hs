{-# OPTIONS -Wall #-}

--------------------------------------------------------------------------------  

module AATree (
  AATree,        -- type of AA search trees
  emptyTree,     -- AATree a
  ex,
  ex1,
  ex2,
  ex3,
  split,         -- Ord a => AATree -> AATree
  skew,          -- AATree a -> AATree a
  get,           -- Ord a => a -> AATree a -> Maybe a
  insert,        -- Ord a => a -> AATree a -> AATree a
  inorder,       -- AATree a -> [a]
  remove,        -- Ord a => a -> AATree a -> AATree a
  size,          -- AATree a -> Int
  height,        -- AATree a -> Int
  checkTree      -- Ord a => AATree a -> Bool
 ) where

--------------------------------------------------------------------------------

type Level = Int

-- AA search trees
data AATree a = Null | Node a Level (AATree a) (AATree a)
  deriving (Eq, Show, Read)


ex :: AATree Int
ex = Node 8 1 (Node 6 2 Null Null) (Node 7 2 Null Null)
ex1 :: AATree Int
ex1 = Node 8 1 (Node 6 2 (Node 7 3 Null Null) Null) Null
ex2 :: AATree Int
ex2 = Node 1 1 Null (Node 7 2 Null (Node 6 3 Null Null))
ex3 :: AATree Int
ex3 = Node 1 1 Null (Node 7 2 Null (Node 6 3 (Node 4 4 Null Null) Null))


emptyTree :: AATree a
emptyTree = Null


get :: Ord a => a -> AATree a -> Maybe a
get _ Null = Nothing
get x (Node v lvl l r)
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
inorder (Node v _ l r) = inorder l ++ v : (inorder r)


size :: AATree a -> Int
size Null             = 0
size (Node _ _ l r) = size l + size r + 1


height :: AATree a -> Int
height Null = 0
height (Node _ _ l r) = 1 + max (height l) (height r)

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
isSorted [x] = True
isSorted (x:y:xs) = if x <= y then isSorted (y:xs) else False

-- Check if the invariant is true for a single AA node
-- You may want to write this as a conjunction e.g.
--   checkLevels node =
--     leftChildOK node &&
--     rightChildOK node &&
--     rightGrandchildOK node
-- where each conjunct checks one aspect of the invariant
checkLevels :: AATree a -> Bool
checkLevels = error "checkLevels not implemented"

isEmpty :: AATree a -> Bool
isEmpty = error "isEmpty not implemented"

leftSub :: AATree a -> AATree a
leftSub = error "leftSub not implemented"

rightSub :: AATree a -> AATree a
rightSub = error "rightSub not implemented"